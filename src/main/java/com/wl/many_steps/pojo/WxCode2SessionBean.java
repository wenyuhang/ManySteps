package com.wl.many_steps.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/27 16:19
 * desc   :
 */
@Getter
@Setter
public class WxCode2SessionBean {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String openid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String session_key;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String unionid;

    private int errcode;
    private String errmsg;
}
