// File: RegistRecord.java
package com.example.narrative.entity;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "regist_record" , // 資料表名稱
        catalog = "narrative_management")  // 資料庫名稱
public class RegistRecord {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne // 報名者可以報名多個讀書會
        @JoinColumn(name = "studies_id")
        private Studies studies;

        @ManyToMany // 報名者可以選購多本書籍
        @JoinTable(name = "regist_book_record", // 紀錄RegistRecord和Book的紀錄，中間表名稱regist_book_record
                joinColumns = @JoinColumn(name = "register_id"), // 報名者的ID創建於中間表 named register_id
                inverseJoinColumns = @JoinColumn(name = "book_id")) // 書籍的ID創建於中間表 named book_id
        private List<Book> book; // 報名者可以選購多本書籍

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

        @Column(name="updated_at")
        private LocalDateTime updatedAt;

        @Column(name ="extra_fields", columnDefinition = "TEXT")
        private String extraFields; //JASON 格式儲存欄位配置
        
        @ManyToOne // 報名者資料插入成員
        @JoinColumn(name = "member_id")
        private Member member;
        // 無參數建構子 (JPA 需要)
        public RegistRecord() {}

        //Lombok 自動產生 getter 和 setter 方法

        // !!error!! Entity不可放入Repository，會被Java認為是一個巢狀類別(Inner class) 導致整個類別無法正常解析getter/setter
        // public interface RegistRecordRepository extends JpaRepository<RegistRecord, Integer> {}
}