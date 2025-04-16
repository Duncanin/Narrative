// RegisterController.java 報名表單管理
package com.example.narrative.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.narrative.Service.BookService;
import com.example.narrative.Service.CardMediumService;
import com.example.narrative.Service.RegisterService;
import com.example.narrative.Service.StudyService;
import com.example.narrative.model.RegistForm;
import com.example.narrative.model.Register;
import com.example.narrative.model.Studies;


@Controller
public class RegisterController {

    @Autowired
    private final RegisterService registerService;
    private final StudyService studyService;
    private final BookService bookService;
    private final CardMediumService cardMediumService;

    public RegisterController(RegisterService registerService,
                                StudyService studyService,
                                BookService bookService,
                                CardMediumService cardMediumService ) {
        this.registerService = registerService;
        this.studyService = studyService;
        this.bookService = bookService;
        this.cardMediumService = cardMediumService;
    }

    //顯示報名表單
    @GetMapping ("/regist")
    public String showRegistrationForm(@RequestParam(value = "studyId", required = false) Integer studyId, Model model) {
        RegistForm registForm = new RegistForm();

        if (studyId != null) {
            Studies study = studyService.findById(studyId);
            registForm.setStudyId(study.getId()); // 預設讀書會 ID
    }

    model.addAttribute("registForm", registForm); // 表單物件
    model.addAttribute("sessions", studyService.findAllWithRemainingQuota()); // 讀書會場次
    model.addAttribute("books", bookService.findAll()); //書籍表單
    model.addAttribute("cardMediums", cardMediumService.findAll()); //卡片選擇

    return "regist/registForm";
    }

    // 處理報名表單
    @PostMapping("/regist")
    public String processRegistration(@ModelAttribute RegistForm registForm, Model model) {
        Register register = new Register();
        
        register.setRegisterName(registForm.getName());
        register.setMailAddress(registForm.getEmail());
        register.setPhoneNum(registForm.getPhone());
        register.setRegistDate(LocalDateTime.now());

        // 設定關聯物件
        register.setStudies(studyService.findById(registForm.getStudyId()));
        register.setBook(bookService.findById(registForm.getBookId()));
        register.setCarMedium(cardMediumService.findById(registForm.getCardMediumId()));

        registerService.saveRegister(register);

        model.addAttribute("message", registForm.getName() + " 報名成功！");
        return "regist/confirmation";
    }

}