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

@Entity
@Table(name = "narrative_registration_system" ,
        catalog = "narrative_management")  // 資料表名稱
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Studies study;

    @Column(name = "register_name", nullable = false, length = 50)
    private String registerName;
    @Column(name = "mail_address", nullable = false, length = 50)
    private String mailAddress;
    @Column(name = "phone_num", nullable = false, length = 15)
    private String phoneNum;
    @Column(name = "school_apart", nullable = false, length = 50)
    private String schoolApart;
    @Column(name = "regist_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 格式化日期
    private LocalDateTime registDate;

    // 無參數建構子 (JPA 需要)
    public Register() {}

    // Getter 和 Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegisterName() { return registerName; }
    public void setRegisterName(String registerName) { this.registerName = registerName; }

    public String getMailAddress() { return mailAddress; }
    public void setMailAddress(String mailAddress) { this.mailAddress = mailAddress; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getSchoolApart() { return schoolApart; }
    public void setSchoolApart(String schoolApart) { this.schoolApart = schoolApart; }

    public LocalDateTime getRegistDate() { return registDate; }
    public void setRegistDate(LocalDateTime registDate) { this.registDate = registDate; }

    public interface RegistRecordRepository extends JpaRepository<Register, Long> {
    
        
    }
}