package com.example.narrative.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
    private static final String USERNAME = "admin" ;
    private static final String PASSWORD = "4321" ;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 返回login.html模板
    }

    @PostMapping("/login")
    public String login (HttpServletRequest request, HttpSession session, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/"; // 登錄成功，重定向到首頁
        } else {
            return "redirect:/user/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //清除session清除會話中的用戶信息
        session.invalidate();
        return "redirect:/user/login";
    }
    

}
