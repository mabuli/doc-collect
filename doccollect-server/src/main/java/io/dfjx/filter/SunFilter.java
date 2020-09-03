package io.dfjx.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "sqg",urlPatterns = {"/pathVar/*"})
public class SunFilter implements Filter {
    @Value("${xframe.url}")
    private String xframeUrl;

    Logger log= LoggerFactory.getLogger(SunFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //resp.setHeader("X-Frame-Options", "ALLOW-FROM http://172.25.117.52:8094/ http://172.25.117.52:37799/ ");
        //resp.setHeader("Content-Security-Policy", "frame-ancestors http://localhost:12100/doc-collect/");
        //resp.setHeader("X-Frame-Options", "http://localhost:12100/doc-collect/");
        log.info("filter 处理中");
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        log.info("filter 销毁");
    }
}
