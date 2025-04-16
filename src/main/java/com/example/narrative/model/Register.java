// File: Register.java
package com.example.narrative.model;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "narrative_registration_system" , // 資料表名稱
        catalog = "narrative_management")  // 資料庫名稱
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne // 報名者可以報名多個讀書會
    @JoinColumn(name = "studies_id")
    private Studies studies;

    @ManyToOne // 報名者可以選購多本書籍
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne // 報名者可以選擇多種卡片
    @JoinColumn(name = "card_medium_id")
    private CardMedium carMedium;

    @Column(name = "register_name", nullable = false, length = 50)
    private String registerName;
    @Column(name = "mail_address", nullable = false, length = 50)
    private String mailAddress;
    @Column(name = "phone_num", nullable = false, length = 15)
    private String phoneNum;
    @Column(name = "school_apart", nullable = false, length = 50)
    private String schoolApart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regist_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 格式化日期
    private LocalDateTime registDate;
    @Column(name ="reserve_book")
    private Boolean reserveBook;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name ="extra_fields", columnDefinition = "TEXT")
    private String extraFields; //JASON 格式儲存欄位配置
    
    // 無參數建構子 (JPA 需要)
    public Register() {}

    //Lombok 自動產生 getter 和 setter 方法
    
    public interface RegistRecordRepository extends JpaRepository<Register, Integer> {
    
    
    }
}