// File: StudyFormFieldService.java
package com.example.narrative.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.narrative.Repository.StudyFormFieldRepository;
import com.example.narrative.model.Studies;
import com.example.narrative.model.StudyFormField;

@Service
public class StudyFormFieldService {

    @Autowired
    private StudyFormFieldRepository repository;

    public List<StudyFormField> getFieldsByStudyId(Integer studyId) {
        return repository.findByStudies_Id(studyId);
    }

    public void saveFieldsForStudy(Integer studyId, List<String> fieldNames, Map<String, String> labelMap, Map<String, String> typeMap, Map<String, Boolean> requiredMap) {
        repository.deleteByStudies_Id(studyId); // 清除原本的欄位設定
    
        for (String name : fieldNames) {
            StudyFormField field = new StudyFormField();
            
            // 建立關聯的 Studies 實體 (只需要 ID 即可)
            Studies study = new Studies();
            study.setId(studyId);
            field.setStudies(study);
    
            // 設定欄位屬性
            field.setFieldName(name);
            field.setFieldLabel(labelMap.get(name));
            field.setFieldType(typeMap.get(name));
            field.setRequired(requiredMap.getOrDefault(name, false));
    
            repository.save(field);
        }
    }
    
}
