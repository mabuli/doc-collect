package io.dfjx.config;

import io.dfjx.core.spring.ControllerBaseInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by cc on 2018/9/9.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${system-config.file-dir}")
    private String fileDir;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerBaseInterceptor()).addPathPatterns("/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+fileDir);
    }
}
