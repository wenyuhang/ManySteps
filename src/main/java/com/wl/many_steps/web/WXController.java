package com.wl.many_steps.web;

import cn.hutool.json.JSONObject;
import com.wl.many_steps.model.ApiResponse;
import com.wl.many_steps.pojo.WXBean;
import com.wl.many_steps.pojo.WXRunDataBean;
import com.wl.many_steps.pojo.WxCode2SessionBean;
import com.wl.many_steps.utils.WXUtils;
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
    private String WX_APP_APPID  = "wx7daac9b7e03f1cf4";
    private String WX_APP_SECRET = "1805a3ea9fddfa40288a5293f5dc9071";




    private static RestTemplate restTemplate;
    static {
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if(httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("UTF-8"));
                break;
            }
        }
    }

    private String session_key;

    @ResponseBody
    @PostMapping(value = "/wxlogin")
    public ApiResponse wxLogin(@Validated @RequestBody WXBean wxBean) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+WX_APP_APPID+"&secret="+WX_APP_SECRET+"&js_code="+wxBean.getCode()+"&grant_type=authorization_code";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());
        JSONObject jsonObject = new JSONObject(response.getBody());
        WxCode2SessionBean bean = jsonObject.toBean(WxCode2SessionBean.class);
        session_key = bean.getSession_key();
//        access_token = "36_SMAl5i1zZH5bsG0t-8RU1a9spFf5T8LsDmYrxdHTkMi388Y6EurShaMWUb1MQVwPFvxgMiFBFX2fhbztA24HRb3m_F2_Am2vK_wV8OKIq90";
//        openid = "o_pKo1BoTiyGuiJ-HIvx9Hl_l9JM";
        return ApiResponse.ofSuccess(bean);
    }

    @ResponseBody
        @PostMapping(value = "/wxRunData")
    public String getWXRunData(@Validated @RequestBody WXRunDataBean wxRunDataBean){
        com.alibaba.fastjson.JSONObject userInfo = WXUtils.getUserInfo(wxRunDataBean.getData(), session_key, wxRunDataBean.getIv());
        System.out.println(userInfo.toJSONString());
        return userInfo.toJSONString();
    }
}
