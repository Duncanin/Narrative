// package com.example.narrative.interceptor;

// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;

// @Component
// public class AuthInterceptor implements HandlerInterceptor {
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         System.out.println("AuthInterceptor==>preHandle: 驗證用戶權限");
//         //獲取請求的URL
//         String requestURL = request.getRequestURI();
//         //放行登入頁面
//         if (requestURL.contains("/admin/login")) {
//             return true; //通過驗證，繼續處理請求
//         }

//         //檢查用戶是否已登入
//         HttpSession session = request.getSession();

//         if(session != null) {
//             Object admin = session.getAttribute("admin");

//             //如果用戶已登入則放行
//             if(admin != null) {
//                 return true;
//             }
//         }

//         //如果用戶未登入則重定向到登入頁面
//         response.sendRedirect(request.getContextPath() + "/admin/login");
//         return false;
//     }

//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//         System.out.println("AuthInterceptor==>postHandle: 處理後紀錄日誌");
//     }

//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//         System.out.println("AuthInterceptor==>afterCompletion: 清理資源或處理異常");
//     }
    
// }
