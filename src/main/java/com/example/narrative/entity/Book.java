package com.example.narrative.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "book" , // 資料表名稱
        catalog = "narrative_management")  // 資料庫名稱
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String author;
    private String publisher;
    private int price;

    @ManyToMany(mappedBy = "book") //mappedBy是指在RegistRecord中有一個book_id的屬性
    // 報名者可以選購多本書籍
    private List<RegistRecord> registerList;
}
