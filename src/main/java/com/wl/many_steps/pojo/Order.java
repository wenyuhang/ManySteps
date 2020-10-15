package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/3 16:31
 * desc   :
 */
@Getter
@Setter
public class Order {
    private int id;
    private int uid;
    private int pid;
    private int adid;
    private int status;
    private String ordercode;
    private String couriernumber;
    private String createdate;

    private Product product;
}
