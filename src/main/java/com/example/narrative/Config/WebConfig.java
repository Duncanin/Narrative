// package com.example.narrative.Config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import com.example.narrative.interceptor.LoginInterceptor;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(new LoginInterceptor())
//                 .addPathPatterns("/**") // 配置攔截的路徑
//                 .excludePathPatterns("/"); // 配置不攔截的路徑
//     }
// }