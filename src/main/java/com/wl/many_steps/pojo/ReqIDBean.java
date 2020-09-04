package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/4 14:12
 * desc   :
 */
@Setter
@Getter
public class ReqIDBean {

    @Min(value = 1,message = "id 参数不能小于1")
    private int id;
}
