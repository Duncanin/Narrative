package com.example.narrative.model;

import lombok.Data;

@Data
public class RegistForm {
    private String name;
    private String email;
    private String phone;
    private Boolean reserveBook;
}
