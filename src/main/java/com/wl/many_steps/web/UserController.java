package com.wl.many_steps.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.Bean;
import com.wl.many_steps.pojo.User;
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
@RestController
@RequestMapping("/userinfo")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public ApiResponse register(@NotBlank(message = "name 不能为空") String name,
                                @NotBlank(message = "headimgurl 不能为空") String headimgurl,
                                @NotBlank(message = "openid 不能为空") String openid,
                                @NotBlank(message = "unionid 不能为空") String unionid,String phone){

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

    @GetMapping("/getUser")
    public String getUserStr(@NotBlank(message = "name 不能为空") String name,
                             @NotBlank(message = "age 不能为空")@Max(value = 99, message = "不能大于99岁") String age) {
        return "name: " + name + " ,age:" + age;
    }

    @PostMapping("/addUser")
    public String getUserStr(@Validated @RequestBody Bean user) {
        return "name: " + user.getName() + ", age:" + user.getSex();
    }
}
