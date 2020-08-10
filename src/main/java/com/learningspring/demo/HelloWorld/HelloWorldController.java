package com.learningspring.demo.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping("/hello-word")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello-int")
    public String getMessagesIni18nFormat(@RequestHeader(name = "Accept-Language", required = false) String locale) {
        return messageSource.getMessage("label.hello",null, new Locale(locale));
    }

    @GetMapping("hello-int2")
    public String getMessagesIni18nFormat2() {
        return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
    }


}
