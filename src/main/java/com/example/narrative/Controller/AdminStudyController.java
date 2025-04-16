// AdminStudyController.java 後台讀書會管理
package com.example.narrative.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.narrative.Service.RegisterService;
import com.example.narrative.Service.StudyService;
import com.example.narrative.model.Register;
import com.example.narrative.model.Studies;

@Controller
@RequestMapping("/admin/studies")
public class AdminStudyController {

    private final StudyService studyService;
    private final RegisterService registerService;
    // 建構子注入 RegisterService
    public AdminStudyController (StudyService studyService, RegisterService registerService) {
        this.studyService = studyService;
        this.registerService = registerService;
    }
    
    @GetMapping("/page")
    public String showStudyPage(Model model) {
        List<Studies> studies = studyService.findAllStudies();
        model.addAttribute("studieslist", studies); // 將報名資料添加到模型中
        return "admin/study/list"; // 返回報名者列表頁面
    }

    @GetMapping("/register/{studyId}") //特定讀書會報名者資料
    public String showRegistersByStudy(@PathVariable Integer studyId, Model model) {
        List<Register> registers = registerService.findByStudyId(studyId);
        model.addAttribute("registrations", registers);
        Studies study = studyService.findById(studyId);
        model.addAttribute("study", study); // 將讀書會資料(名稱)添加到模型中
        return "admin/study/register"; // 導向特定讀書會報名者列表頁面
    }

}
