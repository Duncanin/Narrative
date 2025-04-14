package com.example.narrative.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.narrative.interceptor.AdminInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    // 設定攔截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**") // 配置攔截的路徑，只攔截 /admin 路徑底下
                .excludePathPatterns("/admin/login", "/css/**", "/js/**", "/images/**"); // 排除登入頁和靜態資源
    }
}