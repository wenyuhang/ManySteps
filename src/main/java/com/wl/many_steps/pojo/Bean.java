package com.wl.many_steps.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/19 12:22
 * desc   :
 */
@Data
public class Bean {
    @NotBlank(message = "name 参数不能为空")
    private String name;

    @NotBlank(message = "sex 参数不能为空")
    private String sex;
}
