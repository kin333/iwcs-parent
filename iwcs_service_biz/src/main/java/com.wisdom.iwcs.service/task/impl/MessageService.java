package com.wisdom.iwcs.service.task.impl;

import com.wisdom.base.context.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 国际化工具类
 */
@Service
public class MessageService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    HttpServletRequest request;

    private Locale locale;

    /**
     * 根据配置文件翻译(获取单个国际化翻译值)
     */
    public String get(String msgKey) {
        String currentLang = applicationProperties.getLang().getCurrentLang();
        if (locale == null) {
            if ("en_US".equals(currentLang)) {
                locale = Locale.US;
            } else if ("zh_CN".equals(currentLang)) {
                locale = Locale.CHINA;
            } else {
                locale = LocaleContextHolder.getLocale();
            }
        }
        try {
            return messageSource.getMessage(msgKey, null, locale);
        } catch (Exception e) {
            return msgKey;
        }
    }

    /**
     * 根据请求翻译
     * @param msgKey
     * @return
     */
    public String getByRequest(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, request.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
