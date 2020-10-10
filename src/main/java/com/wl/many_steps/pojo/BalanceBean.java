package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/10 12:04
 * desc   :
 */
@Getter
@Setter
public class BalanceBean {

    @NotBlank(message = "uid 参数不能为空")
    private String uid;

    @Min(value = 1,message = "pid 参数不能小于1")
    private int pid;
}
