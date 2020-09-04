package com.wl.many_steps.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/31 14:46
 * desc   : 后台管理
 */
@Controller
public class AdminController {

    /**
     * 打开后台
     * @return
     */
    @RequestMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    /**
     * 用户登录
     * @param m
     * @return
     */
    @PostMapping("/adminLogin")
    public String adminLogin(Model m,String name,String pwd){
        if ("admin".equals(name)&&"123456".equals(pwd)){
            return "redirect:admin_product_list";
        }else {
            return "admin/admin";
        }
    }

    /**
     * 商品管理
     * @return
     */
    @RequestMapping({"/admin_product_list"})
    public String listProduct() {
        return "admin/product_manage";
    }

    /**
     * 商品编辑
     * @return
     */
    @RequestMapping({"/admin_product_edit"})
    public String editProduct() {
        return "admin/product_edit";
    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping("/admin_user_list")
    public String listUser(){
        return "admin/user_manage";
    }

    /**
     * 订单管理
     * @return
     */
    @RequestMapping("/admin_order_list")
    public String listOrder(){
        return "admin/order_manage";
    }

    /**
     * 用户步数记录
     * @return
     */
    @RequestMapping("/admin_steps_record_list")
    public String stepsRecord(){
        return "admin/user_steps_record";
    }

    /**
     * 用户步数兑换金币记录
     * @return
     */
    @RequestMapping("/admin_steps_coin_list")
    public String stepsCoinConverrt(){
        return "admin/user_stepscoin_convert";
    }
}
