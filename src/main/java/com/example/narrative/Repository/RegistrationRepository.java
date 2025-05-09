// RegistRecordRepository.java
package com.example.narrative.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.narrative.entity.RegistRecord;
import java.time.LocalDateTime;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistRecord, Integer> {
    List<RegistRecord> findByRegisterNameContaining(String registerName); // 這行是用來查詢註冊者名稱的
    List<RegistRecord> findByPhoneNumContaining(String phoneNum); // 這行是用來查詢註冊者電話號碼的
    List<RegistRecord> findByMailAddress(String mailAddress); // 這行是用來查詢註冊者的電子郵件地址
    List<RegistRecord> findByRegistDate(LocalDateTime registDate); // 這行是用來查詢註冊日期的
    // List<RegistRecord> findByAllId(); // 這行是用來查詢所有註冊者的 // Spring 嘗試去找一個 allId 的欄位，但 RegistRecord 顯然沒有這個欄位，所以它就報錯了。
    
    List<RegistRecord> findByRegistDateNull(); // 這行是用來查詢註冊日期為空的註冊者

    @Modifying
    @Transactional
    // 更新註冊者資料
    @Query("UPDATE RegistRecord r SET r.registerName = ?1, r.mailAddress = ?2, r.phoneNum = ?3, r.schoolApart = ?4 WHERE r.id = ?5")
    // Optional<RegistRecord> findByMailAddress(String mailAddress); // 這行是用來查詢註冊者的電子郵件地址
    // 修正命名錯誤
    Optional<RegistRecord> findByRegisterName(String registerName); // 這行是用來查詢註冊者名稱的
    //讓 Spring Boot 會自動幫你產生 SELECT * FROM narrative_registration_system WHERE register_name = ? 的 SQL 查詢

    List<RegistRecord> findByStudies_Id(Integer studyId); // 這行是用來查詢特定讀書會的報名者

}
