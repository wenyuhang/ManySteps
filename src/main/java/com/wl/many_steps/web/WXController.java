package com.wl.many_steps.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.*;
import com.wl.many_steps.service.InviteRelaService;
import com.wl.many_steps.service.StepsCoinService;
import com.wl.many_steps.service.UserService;
import com.wl.many_steps.utils.DateUtils;
import com.wl.many_steps.utils.WXUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/6 15:14
 * desc   :
 */
@Controller
@Validated
public class WXController {
    //公众号配置
//    private String WX_APP_APPID  = "wx2e2e17c9ece2a4cd";
//    private String WX_APP_SECRET = "d3a601ad379167ad2ab071714ee03997";

    //小程序配置
    private String WX_APP_APPID = "wx7daac9b7e03f1cf4";
    private String WX_APP_SECRET = "1805a3ea9fddfa40288a5293f5dc9071";


    @Autowired
    UserService userService;
    @Autowired
    InviteRelaService inviteRelaService;
    @Autowired
    StepsCoinService stepsCoinService;

    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("UTF-8"));
                break;
            }
        }
    }

    /**
     * 微信登录 注册
     * @param wxBean
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/wxlogin")
    public ApiResponse wxLogin(@Validated @RequestBody WXBean wxBean) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + WX_APP_APPID + "&secret=" + WX_APP_SECRET + "&js_code=" + wxBean.getCode() + "&grant_type=authorization_code";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        System.out.println(response.getBody());
//        System.out.println(wxBean.getEncryptedData());
//        System.out.println(wxBean.getIv());
        WxCode2SessionBean bean = JSON.parseObject(response.getBody(), WxCode2SessionBean.class);
        String session_key = bean.getSession_key();
        String openid = bean.getOpenid();
        User user = userService.get(openid);
        if (null == user) {

            JSONObject userInfo = JSONObject.parseObject(WXUtils.getUserInfo(wxBean.getEncryptedData(), session_key, wxBean.getIv()));
            if (userInfo == null) {
                return ApiResponse.of(999, "数据解析错误，请稍后重试", null);
            }
            user = new User();
            String name = (String) userInfo.get("nickName");
            user.setName(name);
            String avatarUrl = (String) userInfo.get("avatarUrl");
            user.setHeadimgurl(avatarUrl);
            String openId = (String) userInfo.get("openId");
            user.setOpenid(openId);
            String unionId = (String) userInfo.get("unionId");
            if (!TextUtils.isEmpty(unionId)) {
                user.setUnionid(unionId);
            }
            user.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
            user.setSession_key(session_key);
            int code = userService.add(user);
            if (code == 0) {
                return ApiResponse.of(999, "操作失败请重试", null);
            }
            //绑定邀请关系
            User inviteUser = userService.get(wxBean.getReferrer());
            if (null != inviteUser) {
                InviteRela inviteRela = new InviteRela();
                inviteRela.setUid(user.getId());
                inviteRela.setInviter_id(wxBean.getReferrer());
                inviteRela.setCreatedate(DateUtils.stampToDate(System.currentTimeMillis()));
                int add = inviteRelaService.add(inviteRela);
                if (add == 0) {
                    System.out.println("绑定邀请关系操作失败");
                } else {
                    //发放奖励
                    stepsCoinService.add(inviteUser.getId(), "邀请" + name + "奖励", 30, 0);
                }
            }
        }else {
            user.setSession_key(session_key);
            userService.update(user);
        }
        return ApiResponse.ofSuccess(user);
    }

    /**
     * 解密微信步数
     * @param wxRunDataBean
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/wxRunData")
    public String getWXRunData(@Validated @RequestBody WXRunDataBean wxRunDataBean) {
        String userInfo = WXUtils.getUserInfo(wxRunDataBean.getData(), "session_key", wxRunDataBean.getIv());

        System.out.println(userInfo);
        return userInfo;
    }
}
