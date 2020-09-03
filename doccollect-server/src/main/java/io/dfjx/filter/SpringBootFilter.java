package io.dfjx.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Component
public class SpringBootFilter {
    //@Bean
    /*public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(sunFilter());
        //设置名称
        filterRegistrationBean.setName("myFilter");
        //设置路径
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }*/

    @Bean
    public Filter sunFilter(){
        return new SunFilter();
    }
}
