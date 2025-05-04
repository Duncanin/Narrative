// AdminStudyController.java 後台讀書會管理
package com.example.narrative.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.narrative.entity.RegistRecord;
import com.example.narrative.entity.Studies;
import com.example.narrative.service.RegistRecordService;
import com.example.narrative.service.StudyService;

import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/admin/studies")
public class AdminStudyController {

    private final StudyService studyService;
    private final RegistRecordService registRecordService;
    // 建構子注入 RegistRecordService
    public AdminStudyController (StudyService studyService, RegistRecordService registRecordService) {
        this.studyService = studyService;
        this.registRecordService = registRecordService;
    }
    
    @GetMapping("/page")
    public String showStudyPage(Model model) {
        // List<Studies> studies = studyService.findAllStudies();
        List<Studies> studies = studyService.findAllByLatestFirst(); // 根據ID降冪排序查詢所有讀書會
        model.addAttribute("studieslist", studies); // 將報名資料添加到模型中
        return "admin/study/list"; // 返回報名者列表頁面
    }

    @GetMapping("/register/{studyId}") //特定讀書會報名者資料
    public String showRegistrationsForStudy(@PathVariable Integer studyId, Model model) {
        List<RegistRecord> registRecords = registRecordService.findByStudyId(studyId);
        model.addAttribute("registrations", registRecords);
        Studies study = studyService.findById(studyId);
        model.addAttribute("study", study); // 將讀書會資料(名稱)添加到模型中
        return "admin/study/register"; // 導向特定讀書會報名者列表頁面
    }

    @GetMapping("/create") //新增讀書會
    public String showCreateStudyPage(Model model) {
        List<Studies> studies = studyService.findAllStudies();
        model.addAttribute("studieslist", studies); // 將報名資料添加到模型中
        return "admin/study/create"; // 導向新增讀書會頁面
    }

    @PostMapping("/create") //
    public String createStudy(@ModelAttribute Studies study, Model model, HttpSession session) {
        // 儲存到session
        session.setAttribute("study", study); //存入session
        return "redirect:/admin/studies/preview"; // 導向預覽
    }

    @GetMapping("/preview")
    public String previewStudy(HttpSession session, Model model) {
        Studies study = (Studies) session.getAttribute("study");
        if (study == null) {
            return "redirect:/admin/studies/create"; // 如果session中沒有study，則重定向到創建頁面
        }
        model.addAttribute("study", study);
        return "admin/study/preview";
    }

    @PostMapping("/confirm")
    public String confirmStudy(HttpSession session) {
        Studies study = (Studies) session.getAttribute("study");
        studyService.save(study);
        session.removeAttribute("study");
        return "redirect:/admin/studies/page";
    }

    @GetMapping("/detail/{studyId}")
    public String showDetailStudyPage(@PathVariable Integer studyId, Model model) {
        Studies study = studyService.findById(studyId);
        if (study != null) {
            model.addAttribute("study", study);
            return "admin/study/detail"; // 導向詳情頁面
        }
        return "redirect:/admin/studies/page"; // 如果找不到讀書會，則重定向到列表頁面
    }

    @PostMapping("detail/{studyId}/edit")
    public String editStudy(@PathVariable Integer studyId, @ModelAttribute Studies study) {
        Studies existingStudy = studyService.findById(studyId);
        if (existingStudy != null) {
            existingStudy.setName(study.getName());
            existingStudy.setDate(study.getDate());
            existingStudy.setLocation(study.getLocation());
            existingStudy.setDeadline(study.getDeadline());
            existingStudy.setQuota(study.getQuota());
            existingStudy.setFee(study.getFee());
            studyService.save(existingStudy);
        }
        return "redirect:/admin/studies/detail/" + studyId; // 編輯後重定向到詳情頁面
    }

    @GetMapping("detail/{studyId}/edit")
    public String showEditForm(@PathVariable Integer studyId, Model model) {
        Studies study = studyService.findById(studyId);
        model.addAttribute("study", study);
        return "admin/study/detail-edit"; // 導向編輯頁面
    }
    

    @GetMapping("/detail/delete/{studyId}")
    public String deleteStudy(@PathVariable Integer studyId) {
        studyService.delete(studyId);
        return "redirect:/admin/studies/page"; // 刪除後重定向到列表頁面
    }



}