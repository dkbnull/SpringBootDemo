package cn.wbnull.springbootdemo.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * I18nUtils
 *
 * @author dukunbiao(null)  2024-03-28
 * https://github.com/dkbnull/SpringBootDemo
 */
public class I18nUtils {

    public static String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/login");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource.getMessage(key, null, locale);
    }

    public static String getMessage(String key, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/login");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource.getMessage(key, args, locale);
    }
}
