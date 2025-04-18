// AdminRegisterController.java 後台報名者管理
package com.example.narrative.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.narrative.entity.RegistRecord;
import com.example.narrative.service.RegistRecordService;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/admin/registrations")
public class AdminRegisterController {
    private final RegistRecordService registRecordService;

    // 建構子注入 RegisterService
    public AdminRegisterController (RegistRecordService registRecordService) {
        this.registRecordService = registRecordService;
    }

    @GetMapping("/page") //所有報名者資料
    public String showRegistrationPage(Model model) {
        List<RegistRecord> registRecords = registRecordService.findAllList();
        model.addAttribute("registrations", registRecords);
        return "admin/registration/list";
    }

}
