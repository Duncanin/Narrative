// RegistController.java 報名表單管理
package com.example.narrative.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.narrative.model.RegistForm;
import com.example.narrative.service.BookService;
import com.example.narrative.service.CardMediumService;
import com.example.narrative.service.RegistRecordService;
import com.example.narrative.service.StudyService;
import com.example.narrative.entity.Book;
import com.example.narrative.entity.CardMedium;
import com.example.narrative.entity.RegistRecord;
import com.example.narrative.entity.Studies;


@Controller
public class RegistController {

    @Autowired
    private final RegistRecordService registRecordService;
    private final StudyService studyService;
    private final BookService bookService;
    private final CardMediumService cardMediumService;

    public RegistController(RegistRecordService registRecordService,
                                StudyService studyService,
                                BookService bookService,
                                CardMediumService cardMediumService ) {
        this.registRecordService = registRecordService;
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

    // 報名表單
    model.addAttribute("registForm", registForm);
    // 讀書會場次
    model.addAttribute("sessions", studyService.findAllWithRemainingQuota());
    // 書籍表單
    model.addAttribute("books", bookService.findAll());
    // 卡片選擇
    model.addAttribute("cardMediums", cardMediumService.findAll());
    return "regist/registForm";
    }

    // 處理報名表單
    @PostMapping("/regist")
    public String processRegistration(@ModelAttribute RegistForm registForm, Model model) {
        RegistRecord registRecord = new RegistRecord();
        
        registRecord.setRegisterName(registForm.getName());
        registRecord.setMailAddress(registForm.getEmail());
        registRecord.setPhoneNum(registForm.getPhone());
        registRecord.setSchoolApart(registForm.getSchoolApart());
        registRecord.setRegistDate(LocalDateTime.now());

        // 設定關聯讀書會
        Studies study = studyService.findById(registForm.getStudyId());
        registRecord.setStudies(study);
        if (registForm.getBookId() != null && !registForm.getBookId().isEmpty()) {
            List<Book> selectedBooks = registForm.getBookId().stream()
                .map(bookService::findById)
                .filter(book -> book != null) // 過濾掉找不到的書籍
                .toList();
                // Java 16 以後可用 .toList()，舊版用 .collect(Collectors.toList())
                model.addAttribute("books", selectedBooks);
        } else {
            model.addAttribute("books", List.of());
        }

        // 設定卡片媒材
        // registRecord.setBook(selectedBooks); // 選擇 RegistRecord 裡的(List<Book>)
        registRecord.setCarMedium(cardMediumService.findById(registForm.getCardMediumId()));

        // 儲存資料
        registRecordService.saveRegister(registRecord);

        //傳遞資料到確認頁
        model.addAttribute("registFor", registForm);
        model.addAttribute("study", study);
        model.addAttribute("card", registRecord.getCarMedium());
        model.addAttribute("message", registForm.getName() + " 報名成功！");

        return "regist/confirmation";
    }
}