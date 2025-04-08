package com.example.narrative.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.narrative.model.Studies;

@Controller
public class StudyControaller {

        //讀書會列表
    @GetMapping("/studies")
    public String listStudies(Model model) {
        List<Studies> studies = new ArrayList<>();
        //模擬讀書會
        for (int i = 1; i < 6; i++) {
            Studies study = new Studies();
            study.setName("讀書會" + i);
            //每個月一號開始
            study.setDate(LocalDate.of(2025, i + 1, 1));
            study.setLocation("台中市" + i);
            study.setDeadline(LocalDate.of(2025, i, 1));
            study.setQuota(30);
            study.setFull(i % 2 == 0); // 模擬偶數場次已滿
            studies.add(study);
        }

        model.addAttribute("studies", studies);

        // 測試輸出，確保 studies 有資料
        System.out.println("讀書會列表：" + studies);

        return "study/list"; // 確保對應 `templates/study/list.html`
    }
}
