package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/4 11:55
 * desc   :
 */
@Getter
@Setter
public class Address {

    private int id;

   @Min(value = 1,message = "uid 参数不能小于1")
    private int uid;

    @NotBlank(message = " receiver 不能为空")
    private String receiver;

    @NotBlank(message = " address 不能为空")
    private String address;

    @NotBlank(message = " mobile 不能为空")
    private String mobile;

    @NotBlank(message = " post 不能为空")
    private String post;

    private String createdate;
}
