package com.wisdom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

/**
 * 配置国际化语言
 */
@Configuration
public class LocaleConfig {
    /**
     * 默认解析器 其中locale表示默认语言
     * @author funsonli
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        // 默认语言为英语
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    /**
     * 默认拦截器 其中lang表示切换语言的参数名
     * @author funsonli
     */
    @Bean
    public WebMvcConfigurer localeInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
                localeInterceptor.setParamName("lang");
                registry.addInterceptor(localeInterceptor);
            }
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {

            }

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

            }

            @Override
            public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

            }

            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

            }

            @Override
            public void addFormatters(FormatterRegistry registry) {

            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {

            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {

            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {

            }

            @Override
            public void configureViewResolvers(ViewResolverRegistry registry) {

            }

            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

            }

            @Override
            public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

            }

            @Override
            public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

            }

            @Override
            public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

            }

            @Override
            public Validator getValidator() {
                return null;
            }

            @Override
            public MessageCodesResolver getMessageCodesResolver() {
                return null;
            }
        };
    }
}
