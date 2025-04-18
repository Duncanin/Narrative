package com.example.narrative.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.narrative.service.StudyFormFieldService;
import com.example.narrative.service.StudyService;

@Controller
@RequestMapping("/admin/study")
public class StudyFormFieldController {

    @Autowired
    private StudyService studyService;

    @Autowired
    private StudyFormFieldService fieldService;

    @GetMapping("/form-fields")
    public String showFormFieldPage(Model model) {
        model.addAttribute("studies", studyService.findAll());
        model.addAttribute("availableFields", getAvailableFields()); // 這是一個 key + label 的列表
        return "admin/study/study-form-fields";
    }

    @PostMapping("/form-fields")
    public String saveFormFields(
        @RequestParam Integer studyId,
        @RequestParam List<String> fieldKeys) {

        Map<String, String> labelMap = getAvailableFields().stream()
            .collect(Collectors.toMap(CustomField::getKey, CustomField::getLabel));
        fieldService.saveFieldsForStudy(studyId, fieldKeys, labelMap, null, null);

        return "redirect:/admin/study/form-fields?success";
    }

    // 模擬內建欄位
    private List<CustomField> getAvailableFields() {
        return List.of(
            new CustomField("reserveBook", "是否預購書籍"),
            new CustomField("carmediumHave", "是否自備交通工具"),
            new CustomField("note", "備註")
        );
    }

    static class CustomField {
        private final String key;
        private final String label;

        public CustomField(String key, String label) {
            this.key = key;
            this.label = label;
        }
        public String getKey() { return key; }
        public String getLabel() { return label; }
    }
}
