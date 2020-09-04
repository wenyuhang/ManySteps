package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.PageBean;
import com.wl.many_steps.pojo.StepsCoin;
import com.wl.many_steps.service.StepsCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
public class StepsCoinController {
    @Autowired
    StepsCoinService stepsCoinService;
//    @Autowired
//    UserService userService;
//
//    private  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//
//    @RequestMapping(value = "/addStepsRecord",method = RequestMethod.POST)
//    public ApiResponse add(String uid,String steps){
//        if (TextUtils.isEmpty(uid)){
//            return ApiResponse.of(999,"uid参数不能为空",null);
//        }
//        if (TextUtils.isEmpty(steps)){
//            return ApiResponse.of(999,"steps参数不能为空",null);
//        }
//        try {
//            Integer.parseInt(steps);
//        }catch (Exception e){
//            return ApiResponse.of(999,"steps参数错误",null);
//        }
//        //校验获取user对象
//        User user = null;
//        try {
//            user = userService.get(Integer.parseInt(uid));
//            if (null==user){
//                throw new Exception();
//            }
//        }catch (Exception e){
//            return ApiResponse.of(999,"uid参数错误，没有此用户",null);
//        }
//        //获取当前为哪天
//        String date = format.format(System.currentTimeMillis());
//
//        StepsRecord stepsRecord = stepsRecordService.get(user,date);
//        if (null==stepsRecord){
//            stepsRecord = new StepsRecord();
//            stepsRecord.setUser(user);
//            stepsRecord.setCreatedate(date);
//            stepsRecord.setSteps(Integer.parseInt(steps));
//        }else {
//            stepsRecord.setSteps(Integer.parseInt(steps));
//        }
//        stepsRecord = stepsRecordService.add(stepsRecord);
//
//        return ApiResponse.ofSuccess(stepsRecord);
//    }
//
//    @RequestMapping(value = "/exchangeSteps",method = RequestMethod.POST)
//    public ApiResponse exchange(String uid,String steps){
//        if (TextUtils.isEmpty(uid)){
//            return ApiResponse.of(999,"uid参数不能为空",null);
//        }
//        if (TextUtils.isEmpty(steps)){
//            return ApiResponse.of(999,"steps参数不能为空",null);
//        }
//        try {
//            Integer.parseInt(steps);
//        }catch (Exception e){
//            return ApiResponse.of(999,"steps参数错误",null);
//        }
//        //校验获取user对象
//        User user = null;
//        try {
//            user = userService.get(Integer.parseInt(uid));
//            if (null==user){
//                throw new Exception();
//            }
//        }catch (Exception e){
//            return ApiResponse.of(999,"uid参数错误，没有此用户",null);
//        }
//        //获取当前为哪天
//        String date = format.format(System.currentTimeMillis());
//
//        StepsRecord stepsRecord = stepsRecordService.get(user,date);
//        int convertSteps = Integer.parseInt(steps)-stepsRecord.getConvertsteps();
//        if (null==stepsRecord){
//            stepsRecord = new StepsRecord();
//            stepsRecord.setUser(user);
//            stepsRecord.setCreatedate(date);
//        }
//        stepsRecord.setSteps(Integer.parseInt(steps));
//        stepsRecord.setConvertsteps(convertSteps);
//        stepsRecord = stepsRecordService.update(stepsRecord);
//
//        return ApiResponse.ofSuccess(stepsRecord);
//    }

    /**
     * 获取用户金币兑换记录
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/stepsCoinConvert", method = RequestMethod.POST)
    public ApiResponse list(@Validated @RequestBody PageBean pageBean) {
        PageHelper.startPage(pageBean.getPage(), pageBean.getSize());
        List<StepsCoin> list = stepsCoinService.list(pageBean.getId());
        PageInfo pageInfo = new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

}
