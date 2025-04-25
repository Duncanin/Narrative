// StydyControaller.java 讀書會列表、讀書會資訊管理
package com.example.narrative.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.narrative.entity.Studies;
import com.example.narrative.service.StudyService;


@Controller
public class StudyController {

    private final StudyService studyService;

    // 建構子注入 StudyService
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    //讀書會資訊
    @GetMapping("/studies")
    public String listStudies(Model model) {
        List<Studies> studies = studyService.findAll(); //從資料庫取得讀書會資料
        model.addAttribute("studies",studyService.findAllWithRemainingQuota()); // 將讀書會資料添加到模型中
        return "study/list"; // 對應 `templates/study/list.html`
    }

}
