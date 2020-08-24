package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;


/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:23
 * desc   :
 */
@Getter
@Setter
public class StepsRecord {

    private int id;

    private User user;

    private int steps;

    private String createdate;

    private int convertsteps;
}
