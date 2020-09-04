package com.wl.many_steps.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.Min;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/9/1 15:29
 * desc   : 商品bean
 */
@Getter
@Setter
public class Product {

    @Min(value = 1,message = "id 参数不能小于1")
    private int id;

    private String name;

    private float coin;

    private float price;

    private int stock;

    private String subTitle;

    private String imageurl;

    private int convertsteps;

    private String createdate;

}
