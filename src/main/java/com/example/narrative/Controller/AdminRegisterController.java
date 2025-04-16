// AdminRegisterController.java 後台報名者管理
package com.example.narrative.Controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.narrative.Service.RegisterService;
import com.example.narrative.model.Register;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/admin/registrations")
public class AdminRegisterController {
    private final RegisterService registerService;

    // 建構子注入 RegisterService
    public AdminRegisterController (RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/page") //所有報名者資料
    public String showRegistrationPage(Model model) {
        List<Register> registers = registerService.findAllList();
        model.addAttribute("registrations", registers);
        return "admin/registration/list";
    }

}
