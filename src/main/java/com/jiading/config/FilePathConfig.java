package com.jiading.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @date 14:47 2021/1/5
 */

@Configuration
public class FilePathConfig implements WebMvcConfigurer {
    @Value("${file.path}")
    private String path; // 对外暴露访问路径

    @Value("${file.address}")
    private String address; // 文件保存真实路径


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(path).addResourceLocations("file:" + address);
    }
}
