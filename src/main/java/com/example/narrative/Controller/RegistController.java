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

import com.example.narrative.entity.RegistRecord;
import com.example.narrative.model.RegistForm;
import com.example.narrative.service.BookService;
import com.example.narrative.service.CardMediumService;
import com.example.narrative.service.RegistRecordService;
import com.example.narrative.service.StudyService;


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

    @GetMapping("/regist")
    public String showForm(Model model) {
        model.addAttribute("registForm", new RegistForm());
        model.addAttribute("sessions", studyService.findAvailableSessions());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("cardMediums", cardMediumService.findAll());
        return "regist/registForm";
    }

    @PostMapping("/regist") // ← 返回修改時進入
    public String handleBackFromPreview(@ModelAttribute RegistForm form, Model model) {
        model.addAttribute("registForm", form);
        model.addAttribute("sessions", studyService.findAvailableSessions());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("cardMediums", cardMediumService.findAll());
        return "regist/registForm";
    }

    @PostMapping("/regist/preview")
    public String showPreview(@ModelAttribute RegistForm form, Model model) {
        model.addAttribute("registForm", form);
        model.addAttribute("study", studyService.findById(form.getStudyId()));
        model.addAttribute("books", form.getBookId() != null
            ? form.getBookId().stream().map(bookService::findById).toList()
            : List.of());
        model.addAttribute("card", cardMediumService.findById(form.getCardMediumId()));
        return "regist/confirmation";
    }

    @PostMapping("/regist/confirm")
    public String confirmRegistration(@ModelAttribute RegistForm form, Model model) {
        RegistRecord record = new RegistRecord();
        record.setRegisterName(form.getName());
        record.setMailAddress(form.getEmail());
        record.setPhoneNum(form.getPhone());
        record.setSchoolApart(form.getSchoolApart());
        record.setStudies(studyService.findById(form.getStudyId()));
        record.setBook(form.getBookId().stream().map(bookService::findById).toList());
        record.setCarMedium(cardMediumService.findById(form.getCardMediumId()));
        record.setRegistDate(LocalDateTime.now());
        registRecordService.saveRegister(record);

        model.addAttribute("registForm", form);
        model.addAttribute("study", record.getStudies());
        model.addAttribute("books", record.getBook());
        model.addAttribute("card", record.getCarMedium());
        model.addAttribute("registRecord", record);
        model.addAttribute("message", form.getName() + " 報名成功！");
        return "regist/success";
    }


}