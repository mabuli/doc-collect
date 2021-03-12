 package io.dfjx.module.auth.filter;
 
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
 import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
 
 @Configuration
 public class InterceptorConfig extends WebMvcConfigurationSupport {
     @Override
     public void addInterceptors(InterceptorRegistry registry){
         LoginInterceptor loginInterceptor = new LoginInterceptor();
         registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                 .excludePathPatterns("/loginback")
                 .excludePathPatterns("/dataCollect/**")
                 .excludePathPatterns("/dataService/**")
                 .excludePathPatterns("/service.html")
                 .excludePathPatterns("/statistics.html")
                 .excludePathPatterns("/converge.html")
                 .excludePathPatterns("/static/**")
                 .excludePathPatterns("/logincas");
         super.addInterceptors(registry);
     }
 
     @Override
     protected void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
         super.addResourceHandlers(registry);
     }
 }
