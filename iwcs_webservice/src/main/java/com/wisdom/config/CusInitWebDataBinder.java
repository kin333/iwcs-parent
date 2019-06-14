package com.wisdom.config;

import com.wisdom.iwcs.commonDto.fliterConOptions.LayerConTypeConverter;
import com.wisdom.iwcs.commonDto.fliterConOptions.LayerConTypeEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 自定义binder,用于mvc接收参数绑定
 *
 * @author ted
 * @create 2019-03-13 下午1:56
 **/
@Configuration
public class CusInitWebDataBinder {
    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        //注册自定义enum　dataBinder
        webdataBinder.registerCustomEditor(LayerConTypeEnum.class, new LayerConTypeConverter());
    }
}
