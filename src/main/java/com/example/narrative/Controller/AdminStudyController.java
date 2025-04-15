// AdminStudyController.java 後台讀書會管理

package com.example.narrative.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.narrative.Service.StudyService;
import com.example.narrative.model.Studies;

@Controller
@RequestMapping("/admin/studies")
public class AdminStudyController {

    private final StudyService studyService;
    // 建構子注入 RegisterService
    public AdminStudyController (StudyService studyService) {
        this.studyService = studyService;
    }
    
    @GetMapping("/page")
    public String showStudyPage(Model model) {
        List<Studies> studies = studyService.findAllStudies();
        model.addAttribute("studieslist", studies); // 將報名資料添加到模型中
        return "admin/study/list"; // 返回報名者列表頁面
    }
    
}
