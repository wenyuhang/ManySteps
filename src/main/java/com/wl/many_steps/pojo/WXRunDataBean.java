package com.wl.many_steps.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/29 11:48
 * desc   :
 */
@Data
public class WXRunDataBean {
    @NotBlank(message = "data 参数不能为空")
    private String data;

    @NotBlank(message = "iv 参数不能为空")
    private String iv;
}
