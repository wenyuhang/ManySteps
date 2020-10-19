package com.wl.many_steps.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:15
 * desc   :
 */
@Getter
@Setter
public class User {

    private int id;

    private String name;

    private String headimgurl;

    private String openid;

    private String unionid;

    @JsonBackReference
    private String session_key;

    private String access_token;

    private String phone;

    private int steps_total;

    private float coin_total;

    private int invite_total;

    private String createdate;
}
