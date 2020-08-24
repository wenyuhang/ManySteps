package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.User;
import com.wl.many_steps.service.UserService;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:46
 * desc   :
 */
@RestController
@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public ApiResponse register(String name,String headimgurl,String openid,String unionid,String phone){
        if (TextUtils.isEmpty(name)){
            return ApiResponse.of(999,"name参数不能为空",null);
        }
        if (TextUtils.isEmpty(headimgurl)){
            return ApiResponse.of(999,"headimgurl参数不能为空",null);
        }
        if (TextUtils.isEmpty(openid)){
            return ApiResponse.of(999,"openid参数不能为空",null);
        }

        //检测用户是否注册
        User user = userService.get(openid);
        if (null==user){
            user = new User();
            user.setName(name);
            user.setHeadimgurl(headimgurl);
            user.setOpenid(openid);
            user.setCreatedate(String.valueOf(System.currentTimeMillis()));
            if (!TextUtils.isEmpty(unionid)){
                user.setUnionid(unionid);
            }
            if (!TextUtils.isEmpty(phone)){
                user.setPhone(phone);
            }
           int id = userService.add(user);
            System.out.println("====>"+id);
        }
        return ApiResponse.ofSuccess(user);
    }


    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    public ApiResponse list(){
        PageHelper.startPage(1,10);
        List<User> list = userService.list();
        System.out.println("list is size:"+list.size());
        PageInfo pageInfo=new PageInfo(list);
        return ApiResponse.ofSuccess(pageInfo);
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello world";
    }
}
