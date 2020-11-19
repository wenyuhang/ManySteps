package com.wl.many_steps.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.*;
import com.wl.many_steps.service.StepsCoinService;
import com.wl.many_steps.service.StepsRecordService;
import com.wl.many_steps.service.UserService;
import com.wl.many_steps.utils.CoinUtils;
import com.wl.many_steps.utils.DateUtils;
import com.wl.many_steps.utils.WXUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 17:12
 * desc   :
 */
@Validated
@RestController
@RequestMapping("/userinfo")
public class StepsRecordController {
    @Autowired
    StepsRecordService stepsRecordService;
    @Autowired
    UserService userService;
    @Autowired
    StepsCoinService stepsCoinService;

    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    //单日最高转换20000步
    private static int canConvertSteps = 20000;

    /**
     * 计算今日可兑换步数
     *
     * @param wxRunDataBean
     * @return
     */
    @PostMapping(value = "/getRunSteps")
    public ApiResponse getSteps(@Validated @RequestBody WXRunDataBean wxRunDataBean) {
        //可兑换的步数
        int steps = 0;
        //今日运动步数
        int stepsToday = 0;
        //今日已兑换步数
        int convertedsteps = 0;

        //校验用户
        User user = userService.get(wxRunDataBean.getUid());
        if (null == user) {
            return ApiResponse.of(999, "用户不存在", null);
        }
        //获取当前为哪天
        String date = format.format(System.currentTimeMillis());
        StepsRecord stepsRecord = stepsRecordService.get(wxRunDataBean.getUid(), date);
        if (null != stepsRecord) {
            stepsToday = stepsRecord.getSteps();
            convertedsteps = stepsRecord.getConvertedsteps();
        } else {
            stepsRecord = new StepsRecord();
            stepsRecord.setUid(wxRunDataBean.getUid());
            stepsRecord.setRundate(date);
            stepsRecord.setSteps(stepsToday);
            stepsRecord.setConvertedsteps(convertedsteps);
        }
        //解密运动数据
        String json = WXUtils.getUserInfo(wxRunDataBean.getData(), user.getSession_key(), wxRunDataBean.getIv());
        RunEncryptedData runData = JSON.parseObject(json, RunEncryptedData.class);
        if (null != runData) {
            List<RunEncryptedData.StepInfoListBean> stepInfoList = runData.getStepInfoList();
            if (!stepInfoList.isEmpty()) {
                RunEncryptedData.StepInfoListBean stepInfoListBean = stepInfoList.get(stepInfoList.size() - 1);
                //比较日期
                long timestamp = stepInfoListBean.getTimestamp();
                if (date.equals(format.format(timestamp * 1000))) {
                    int runSteps = stepInfoListBean.getStep();
                    if (runSteps > stepsToday) {
                        stepsToday = runSteps;
                    }
                }
            }
        }

        //检查是否超出限制  可转换步数超出2w步重置为上限2w
        if (canConvertSteps < stepsToday) {
//            stepsToday = canConvertSteps;
            //计算可兑换步数
            steps = canConvertSteps - convertedsteps;
        }else {
            //计算可兑换步数
            steps = stepsToday - convertedsteps;
        }

        //插入数据
        stepsRecord.setSteps(stepsToday);
        int code = 0;
        if (TextUtils.isEmpty(stepsRecord.getCreatedate())) {
            //新增
            stepsRecord.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
            code = stepsRecordService.add(stepsRecord);
        } else {
            //更新
            stepsRecord.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
            code = stepsRecordService.update(stepsRecord);
        }

        if (code == 0) {
            System.out.println("微信步数数据插入失败");
        }

        //注册用户超过1000奖励减半
        if (userService.getUserCount() > 1000) {
            return ApiResponse.of(200, "当前试运营阶段邀请好友可获得10金币奖励", steps);
        } else {
            return ApiResponse.of(200, "当前运营阶段邀请好友可获得20金币奖励", steps);
        }
    }

    /**
     * 步数转金币
     *
     * @param bean
     * @return
     */
    @PostMapping(value = "/convertSteps")
    public ApiResponse exchange(@Validated @RequestBody ReqIDBean bean) {
        //校验用户
        User user = userService.get(bean.getId());
        if (null == user) {
            return ApiResponse.of(999, "用户不存在", null);
        }
        //获取当前为哪天
        String date = format.format(System.currentTimeMillis());
        StepsRecord stepsRecord = stepsRecordService.get(bean.getId(), date);
        if (null == stepsRecord) {
            return ApiResponse.of(999, "兑换金币出现问题，请退出重试", null);
        }
        //步数转换金币
        int steps = 0;
        int stepsToday = stepsRecord.getSteps();
        //检查是否超出限制  可转换步数超出2w步重置为上限2w
        if (canConvertSteps < stepsToday) {
            steps = canConvertSteps - stepsRecord.getConvertedsteps();
        }else {
            steps = stepsToday - stepsRecord.getConvertedsteps();
        }

        float coin = CoinUtils.calc(steps);
        int convertedsteps = stepsRecord.getConvertedsteps() + steps;
        stepsRecord.setConvertedsteps(convertedsteps);
        stepsRecord.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
        int code = stepsRecordService.update(stepsRecord);
        if (code == 0) {
            System.out.println("微信步数数据更新失败");
            return ApiResponse.of(999, "兑换金币出现问题，请退出重试", null);
        }
        //发放奖励
        stepsCoinService.add(user.getId(), "步数转金币", coin, 0);
        return ApiResponse.ofSuccess(0);
    }

    /**
     * 获取用户步数记录
     *
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/stepsRecord", method = RequestMethod.POST)
    public ApiResponse list(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<StepsRecord> list = stepsRecordService.list(pageBean.getId());
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

}
