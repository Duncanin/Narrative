package com.example.narrative.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.narrative.Service.RegisterService;
import com.example.narrative.model.RegistForm;
import com.example.narrative.model.Register;

@Controller
public class RegisterController {

    @Autowired
    private final RegisterService registerService;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    //顯示報名表單
    @GetMapping ("/regist")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registForm", new RegistForm());
        return "regist/registForm"; // 對應 registForm.html
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


    // 提交報名
    // @PostMapping("/submit")
    // public ResponseEntity<String> registerUser(@RequestBody Register register) {
    //     registerService.saveRegister(register);
    //     return ResponseEntity.ok("報名成功！");
    // }

        // 查詢報名者資料
    @GetMapping("/search")
    public ResponseEntity<Register> getUserByEmail(@RequestParam String email) {
        List<Register> register = registerService.getRegisterByEmail(email);
        return ResponseEntity.of(Optional.ofNullable(register.get(0)));
    }

    // 取得所有報名資料
    @GetMapping("/admin/register/list")
    public ResponseEntity<List<Register>> getAllRegisters() {
        return ResponseEntity.ok(registerService.getAllRegisters());
    }

    //沒有進入mysql
    @RequestMapping("/registers")
    public String insert() {
        String sql = "INSERT INTO narrative_registration_system(Id,register_name) VALUES (3,'Duncan')";
        Map<String, Object> map = new HashMap<>();
        namedParameterJdbcTemplate.update(sql, map);
        return "執行 INSERT sql";
    }


    
}