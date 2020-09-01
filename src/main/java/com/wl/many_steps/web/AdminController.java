package com.wl.many_steps.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/31 14:46
 * desc   : 后台管理
 */
@Controller
public class AdminController {

    @RequestMapping("/hello")
    public String admin(Model m) {
        m.addAttribute("name", "wyh18271");
        return "hello";
    }
}
