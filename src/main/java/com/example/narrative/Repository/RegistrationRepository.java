// RegisterRepository.java
package com.example.narrative.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.narrative.model.Register;
import java.time.LocalDateTime;

@Repository
public interface RegistrationRepository extends JpaRepository<Register, Long> {
    List<Register> findByRegisterNameContaining(String registerName); // 這行是用來查詢註冊者名稱的
    List<Register> findByPhoneNumContaining(String phoneNum); // 這行是用來查詢註冊者電話號碼的
    List<Register> findByMailAddress(String mailAddress); // 這行是用來查詢註冊者的電子郵件地址
    List<Register> findByRegistDate(LocalDateTime registDate); // 這行是用來查詢註冊日期的
    // List<Register> findByAllId(); // 這行是用來查詢所有註冊者的 // Spring 嘗試去找一個 allId 的欄位，但 Register 顯然沒有這個欄位，所以它就報錯了。
    
    List<Register> findByRegistDateNull(); // 這行是用來查詢註冊日期為空的註冊者

    @Modifying
    @Transactional
    // 更新註冊者資料
    @Query("UPDATE Register r SET r.registerName = ?1, r.mailAddress = ?2, r.phoneNum = ?3, r.schoolApart = ?4 WHERE r.id = ?5")
    // Optional<Register> findByMailAddress(String mailAddress); // 這行是用來查詢註冊者的電子郵件地址
    // 修正命名錯誤
    Optional<Register> findByRegisterName(String registerName); // 這行是用來查詢註冊者名稱的
    //讓 Spring Boot 會自動幫你產生 SELECT * FROM narrative_registration_system WHERE register_name = ? 的 SQL 查詢


}
