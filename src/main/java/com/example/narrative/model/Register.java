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
    @Column(name = "carmedium_have", nullable = false)
    private Boolean carmediumHave;
    @Column(name = "regist_a_session", nullable = false)
    private String registASession;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regist_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 格式化日期
    private LocalDateTime registDate;
    @Column(name ="reserve_book")
    private String reserveBook;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;


    
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

    public Boolean getCarmediumHave() { return carmediumHave; }
    public void setCarmediumHave(Boolean carmediumHave) { this.carmediumHave = carmediumHave; }

    public LocalDateTime getRegistDate() { return registDate; }
    public void setRegistDate(LocalDateTime registDate) { this.registDate = registDate; }

    public String getRegistASession() { return registASession; }
    public void setRegistASession(String registASession) { this.registASession = registASession; }
    
    public String getReserveBook() { return reserveBook; }
    public void setReserveBook(String reserveBook) { this.reserveBook = reserveBook; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    

    public interface RegistRecordRepository extends JpaRepository<Register, Long> {
    
    
    }
}