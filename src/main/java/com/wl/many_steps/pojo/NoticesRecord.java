package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/14 16:58
 * desc   :
 */
@Getter
@Setter
public class NoticesRecord {

    private int id;

    private int uid;

    private boolean p_status;

    private String p_desc;

    private int steps;

    private float coin;

    private Timestamp create_time;

    private Timestamp update_time;

    private User user;
}
