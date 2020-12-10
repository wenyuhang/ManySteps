package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/9 15:30
 * desc   :
 */
@Getter
@Setter
public class RankListBean {
    private List<User> rankList;
    private int userRanking;
}
