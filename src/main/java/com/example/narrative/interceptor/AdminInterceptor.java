package com.example.narrative.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //獲取請求的URL
        // String requestURL = request.getRequestURI();
    //放行登入頁面
        // if (requestURL.contains("/admin/login")) {
        //     return true;
        // }
    // 0414 把攔截器配置放在WebConfig.java中，讓攔截器只管驗證邏輯
    
    // 檢查用戶是否已登入,取得session
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            return true; // 通過驗證，繼續處理請求
        }

        //如果用戶未登入則重定向到登入頁面
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }
}
