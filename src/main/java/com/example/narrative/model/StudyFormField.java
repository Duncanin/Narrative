// StudyFormField.java
package com.example.narrative.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "study_form_fields", catalog = "narrative_management") // 資料表名稱
public class StudyFormField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fieldName;
    private String fieldLabel;
    private String fieldType;
    private boolean isRequired;

    @ManyToOne
    @JoinColumn(name = "studies_id")
    private Studies studies;

    
}
