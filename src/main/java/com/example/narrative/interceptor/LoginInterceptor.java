// package com.example.narrative.interceptor;

// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;

// @Component
// public class LoginInterceptor implements HandlerInterceptor {

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         //獲取請求的URL
//         String requestURL= request.getRequestURI();
//         // // 假設你有一個判斷用戶是否已經登錄的邏輯
//         // boolean isLoggedIn = checkUserLoggedIn(request);

//         // if (!isLoggedIn) {
//         //     // 如果用戶未登錄，重定向到登錄頁面
//         //     response.sendRedirect("/user/login");
//         //     return false; // 返回false以中斷請求
//         // }
//         if (requestURL.contains("admin/login")) {
//             return true; // 返回true以繼續處理請求
//         }
//         //檢查用戶是否登入
//         HttpSession session = request.getSession();
//         if (session != null) {
//             Object user = session.getAttribute("user");

//             //如果用戶已登入，則允許請求繼續
//             if (user != null) {
//                 return true;
//             }
//         }
//         //如果用戶未登入，則重定向到登入頁面
//         response.sendRedirect(request.getContextPath() + "/admin/login");
//         return false;
//     }

//     // private boolean checkUserLoggedIn(HttpServletRequest request) {
//     //     // 這裡應該有你的判斷用戶是否已經登錄的邏輯
//     //     // 例如檢查session或token
//     //     return request.getSession().getAttribute("user") != null;
//     // }
// }