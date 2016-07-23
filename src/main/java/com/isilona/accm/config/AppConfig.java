package com.isilona.accm.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * Spring 3rd party REST application context configuration.
 * 
 * @author iivanov
 *
 */
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Maps resources path to webapp/resources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Configure bean to convert JSON to POJO and vice versa.
     */
    @Bean
    public MappingJackson2HttpMessageConverter jsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    /**
     * Configure to plug-in JSON as request and response in method handler
     */
    @Bean
    public RequestMappingHandlerAdapter jsonRequestMapping() {
        RequestMappingHandlerAdapter rmha = new RequestMappingHandlerAdapter();
        HttpMessageConverter<?>[] messageConverters = new HttpMessageConverter<?>[] { jsonMessageConverter() };
        rmha.setMessageConverters(Arrays.asList(messageConverters));
        return rmha;
    }

}
