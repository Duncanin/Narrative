// RegisterController.java 報名表單管理
package com.example.narrative.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.narrative.Service.RegisterService;
import com.example.narrative.Service.StudyService;
import com.example.narrative.model.RegistForm;
import com.example.narrative.model.Studies;


@Controller
public class RegisterController {

    @Autowired
    private final RegisterService registerService;
    private final StudyService studyService;

    public RegisterController(RegisterService registerService, StudyService studyService) {
        this.registerService = registerService;
        this.studyService = studyService;
    }

    //顯示報名表單
    @GetMapping ("/regist")
    public String showRegistrationForm(@RequestParam(value = "studyId", required = false) Integer studyId, Model model) {
        RegistForm registForm = new RegistForm();

        if (studyId != null) {
        Studies study = studyService.findById(studyId);
        registForm.setRegistASession(study.getName()); // 預選場次名稱
    }

    model.addAttribute("registForm", registForm); // 表單物件
    model.addAttribute("sessions", studyService.findAll()); // 所有讀書會場次放進下拉式選單

    return "regist/registForm";
    }

    @PostMapping("/regist")
    public String processRegistration(@ModelAttribute("regist/registForm") RegistForm registForm, Model model) {
        try {
            //基本驗證
            if (registForm.getName() == null || registForm.getName().trim().isEmpty()) {
                model.addAttribute("error", "請輸入報名者姓名");
                return "regist/registForm";
            }
            if (registForm.getEmail() == null || registForm.getEmail().trim().isEmpty()) {
                model.addAttribute("error", "請輸入聯絡信箱");
                return "regist/registForm";
            }
            if (registForm.getPhone() == null || registForm.getPhone().trim().isEmpty()) {
                model.addAttribute("error", "請輸入聯絡電話");
                return "regist/registForm";
            }
            //處理預約邏輯(報名成功資訊)
            // String message = String.format("已成功報名， 姓名 : %s， 聯絡信箱： %s， 聯絡電話： %s， 是否預約書籍： %s",
            //     registForm.getName(), registForm.getEmail(), registForm.getPhone(), registForm.getReserveBook() ? "是" : "否");
            String message = String.format(" %s 已成功報名>", registForm.getName());
            model.addAttribute("message", message);
            model.addAttribute("registForm", registForm);

            return "regist/confirmation";

        } catch (Exception e) {
            model.addAttribute("error", "預約處理時發生錯誤：" + e.getMessage());
            return "regist/registForm";
        }
    }

    // 取得所有報名資料 0411 modify from getAllRegisters to findAllList
    // 0414 worked repeat method, comment out
    // @GetMapping("/admin/register/list")
    // public ResponseEntity<List<Register>> findAllList() {
    //     return ResponseEntity.ok(registerService.findAllList());
    // }

}