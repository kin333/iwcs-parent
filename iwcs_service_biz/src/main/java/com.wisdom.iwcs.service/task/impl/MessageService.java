package com.wisdom.iwcs.service.task.impl;

import com.wisdom.base.context.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

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

    /**
     * 获取单个国际化翻译值
     */
    public String get(String msgKey) {
        String currentLang = applicationProperties.getLang().getCurrentLang();
        Locale locale;
        if ("en_US".equals(currentLang)) {
            locale = Locale.US;
        } else if ("zh_CN".equals(currentLang)) {
            locale = Locale.CHINA;
        } else {
            locale = LocaleContextHolder.getLocale();
        }
        try {
            return messageSource.getMessage(msgKey, null, locale);
        } catch (Exception e) {
            return msgKey;
        }
    }
}
