// AdminRegisterController.java 後台報名者管理
package com.example.narrative.Controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/page")
    public String showRegistrationPage(Model model) {
        List<Register> registers = registerService.findAllList();
        model.addAttribute("registrations", registers); // 將報名資料添加到模型中
        return "admin/registration/list";
    }

    @GetMapping("/page/edit")
    public String editRegistrationPage(@RequestParam Integer registerId, Model model) {
        Register register = registerService.getById(registerId);
        model.addAttribute("registrations", register); // 將報名資料添加到模型中
        return "admin/registration/edit"; // 返回編輯頁面
    }
    
}
