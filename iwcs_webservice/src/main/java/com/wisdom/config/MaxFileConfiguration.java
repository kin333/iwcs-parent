package com.wisdom.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author Devin
 * @date 2017/12/20.
 */
@Configuration
public class MaxFileConfiguration {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        //KB,MB
        factory.setMaxFileSize("200MB");
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("200MB");

        return factory.createMultipartConfig();
    }
}
