package com.example.narrative.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 假設你有一個判斷用戶是否已經登錄的邏輯
        boolean isLoggedIn = checkUserLoggedIn(request);

        if (!isLoggedIn) {
            // 如果用戶未登錄，重定向到登錄頁面
            response.sendRedirect("/user/login");
            return false; // 返回false以中斷請求
        }

        return true; // 返回true以繼續處理請求
    }

    private boolean checkUserLoggedIn(HttpServletRequest request) {
        // 這裡應該有你的判斷用戶是否已經登錄的邏輯
        // 例如檢查session或token
        return request.getSession().getAttribute("user") != null;
    }
}