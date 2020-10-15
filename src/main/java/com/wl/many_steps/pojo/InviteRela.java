package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/14 15:57
 * desc   :
 */
@Getter
@Setter
public class InviteRela {
    private int id;
    private int uid;
    private int inviter_id;
    private String createdate;

    private User user;
}
