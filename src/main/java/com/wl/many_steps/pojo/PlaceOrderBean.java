package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/12 17:43
 * desc   :
 */
@Getter
@Setter
public class PlaceOrderBean {

    @Min(value = 1,message = "uid 参数不能小于1")
    private int uid;

    @Min(value = 1,message = "pid 参数不能小于1")
    private int pid;

    @Min(value = 1,message = "adid 参数不能小于1")
    private int adid;
}
