package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.InviteRela;
import com.wl.many_steps.pojo.PageBean;
import com.wl.many_steps.pojo.ReqIDBean;
import com.wl.many_steps.pojo.User;
import com.wl.many_steps.service.InviteRelaService;
import com.wl.many_steps.service.StepsCoinService;
import com.wl.many_steps.service.UserService;
import org.apache.http.util.TextUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:46
 * desc   :
 */
@Validated
@RestController
@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    InviteRelaService inviteRelaService;
    @Autowired
    StepsCoinService stepsCoinService;


    /**
     * 获取用户列表
     * @param pageBean
     * @return
     */
    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    public ApiResponse list(@Validated @RequestBody PageBean pageBean){
        PageHelper.startPage(pageBean.getPage(),pageBean.getSize());
        List<User> list = userService.list();
        PageInfo pageInfo=new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

    /**
     * 获取用户信息
     * @param req
     * @return
     */
    @PostMapping(value = "/getUserInfo")
    public ApiResponse get(@Validated @RequestBody ReqIDBean req){
        User user = userService.get(req.getId());
        if (null==user){
            return ApiResponse.of(999,"用户不存在",null);
        }
        //计算金币总和
        float coin_total = stepsCoinService.sum(user.getId());
        //计算邀请人数个和
        int invite_total = inviteRelaService.getInviteNum(user.getId());
        //更新用户数据库
        user.setCoin_total(coin_total);
        user.setInvite_total(invite_total);
        int code = userService.update(user);
        if (code==0){
            return ApiResponse.of(999,"操作失败，请稍后重试",null);
        }
        return ApiResponse.ofSuccess(user);
    }


    /**
     * 获取用户的邀请记录
     * @param pageBean
     * @return
     */
    @PostMapping(value = "/getInviteRecord")
    public ApiResponse getInviteRecord(@Validated @RequestBody PageBean pageBean){
        PageHelper.startPage(pageBean.getPage(),pageBean.getSize());
        List<InviteRela> list = inviteRelaService.getByInviteid(pageBean.getId());
        PageInfo pageInfo=new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

}
