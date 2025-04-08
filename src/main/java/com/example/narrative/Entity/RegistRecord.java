// package com.example.narrative.Entity;

// import org.springframework.data.jpa.repository.JpaRepository;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table (name = "narrative_registration_system",
//         catalog = "narrative_management")  // 資料表名稱
// public class RegistRecord {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     @Column(name = "register_name", nullable = false)
//     private String registerName;
//     @Column(name = "mail_address", nullable = false)
//     private String mailAddress;
//     @Column(name = "phone_num", nullable = false)
//     private String phoneNum;

//     public Long getId() {
//         return id;
//     }
//     public void setId(Long id) {
//         this.id = id;
//     }
//     public String getRegisterName() {
//         return registerName;
//     }
//     public void setRegisterName(String registerName) {
//         this.registerName = registerName;
//     }
//     public String getMailAddress() {
//         return mailAddress;
//     }
//     public void setMailAddress(String mailAddress) {
//         this.mailAddress = mailAddress;
//     }
//     public String getPhoneNum() {
//         return phoneNum;
//     }
//     public void setPhoneNum(String phoneNum) {
//         this.phoneNum = phoneNum;
//     }
    
//     public interface RegistRecordRepository extends JpaRepository<RegistRecord, Long> {
        
//     }
// }
