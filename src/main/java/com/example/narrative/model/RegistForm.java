package com.example.narrative.model;

import java.time.LocalDateTime;
import java.util.List;


import lombok.Data;

@Data
public class RegistForm {
    private String name;
    private String email;
    private String phone;
    private String schoolApart;
    
    private Integer studyId;
    private List<Integer> bookId;
    private Integer cardMediumId;
    private LocalDateTime registDate;
}