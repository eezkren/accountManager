package com.isilona.accm.config.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.isilona.accm.config.AppConfig;

/**
 * Spring "web" application context configuration.
 * 
 * @author iivanov
 *
 */
@EnableWebMvc
@ComponentScan("com.isilona.accm.web")
public class WebConfig extends AppConfig {

    @Autowired
    private MessageSource messageSource;

    @Resource
    private Environment environment;

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

}