package com.example.narrative.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.narrative.Service.RegisterService;
import com.example.narrative.model.Register;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RequestMapping("/admin")
@Controller
public class UserController {
    private static final String USERNAME = "admin" ;
    private static final String PASSWORD = "4321" ;

    private final RegisterService registerService;

    public UserController (RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/dashboard")
    public String userDashboard(Model model) {
        List<Register> registers = registerService.getAllRegisters();
        model.addAttribute("registers", registers);
        return "admin";
    }


    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // 返回login.html模板
    }

    @PostMapping("/login")
    public String login (HttpServletRequest request, HttpSession session, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            session.setAttribute("admin", username);
            return "redirect:/admin"; // 登錄成功，重定向到首頁
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
