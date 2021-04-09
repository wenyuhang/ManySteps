package com.wl.many_steps.pojo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/4 11:55
 * desc   : 版本审核
 */
@Getter
@Setter
public class VersionReview {

    private int id;

    private int version;

    private String desc;

    private Timestamp create_time;

    private Timestamp update_time;
}
