package com.example.narrative.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.narrative.entity.RegistRecord;
import com.example.narrative.repository.RegistrationRepository;

@Service
public class RegistRecordService {

    @Autowired
    private RegistrationRepository repository;


    public void saveRegistRecord(String registerName, String mailAddress, String phoneNumber, String schoolApart) {
        RegistRecord registRecord = new RegistRecord();
        registRecord.setId(0); // 設定 ID 為 0，讓 JPA 自動生成
        registRecord.setRegisterName(registerName);
        registRecord.setMailAddress(mailAddress);
        registRecord.setPhoneNum(phoneNumber);
        registRecord.setSchoolApart(schoolApart);
        repository.save(registRecord);
    }

    public RegistRecordService(RegistrationRepository repository) {
        this.repository = repository;
    }

    // public Register getId(Integer registerId) {
    //     return repository.getId(registerId);
    // }

    // 存儲報名資料
    public RegistRecord saveRegister(RegistRecord registRecord) {
        return repository.save(registRecord);
    }

    // 透過 email 查詢報名者
    public List<RegistRecord> getRegisterByEmail(String email) {
        return repository.findByMailAddress(email);
    }

    // 取得所有報名資料（管理員用）
    public List<RegistRecord> findAllList() {
        return repository.findAll();
    }

    public RegistRecord creaRegistRecord (RegistRecord registRecord) {
        return repository.save(registRecord);
    }

    public RegistRecord getById(Integer registerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<RegistRecord> findByStudyId(Integer studyId) {
        return repository.findByStudies_Id(studyId);
    }
}