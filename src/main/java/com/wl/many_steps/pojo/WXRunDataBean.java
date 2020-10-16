package com.wl.many_steps.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/29 11:48
 * desc   :
 */
@Getter
@Setter
public class WXRunDataBean {
    @Min(value = 1, message = "uid 参数不能为空")
    private int uid;

    @NotBlank(message = "data 参数不能为空")
    private String data;

    @NotBlank(message = "iv 参数不能为空")
    private String iv;
}
