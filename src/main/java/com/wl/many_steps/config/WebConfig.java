package com.wl.many_steps.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/10/26 10:34
 * desc   :
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${pro.img.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /home/file/**为前端URL访问路径 后面 file:xxxx为本地磁盘映射
        registry.addResourceHandler("/img/product/**").addResourceLocations("file:"+path);
    }
}
