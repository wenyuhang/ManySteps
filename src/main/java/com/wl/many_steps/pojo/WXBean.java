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
public class WXBean {
    @NotBlank(message = "code 参数不能为空")
    private String code;

    private String encryptedData;//加密数据

    private String iv;//解密向量

    private int referrer;//推荐人id
}
