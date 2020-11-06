package com.wl.many_steps.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/1 16:56
 * desc   :
 */
@Data
public class PageBean {
    @Min(value = 1,message = "page 参数不能小于1")
    private int page;

    @Min(value = 1,message = "size 参数不能小于1")
    @Max(value = 50,message = "size 参数不能大于50")
    private int size;

    private int id;

    //是否是APP端请求  默认是 0  APP为 1
    private int isApp;
}
