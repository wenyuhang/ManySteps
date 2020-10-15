package com.wl.many_steps.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/17 15:23
 * desc   :
 */
@Getter
@Setter
public class StepsCoin {

    private int id;

    private int uid;

    private String tran_desc;

    private float coin;

    private String rundate;

    private int convertsteps;

    private String createdate;


}
