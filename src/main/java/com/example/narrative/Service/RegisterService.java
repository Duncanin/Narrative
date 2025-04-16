package com.example.narrative.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.narrative.model.Register;
import com.example.narrative.Repository.RegistrationRepository;

@Service
public class RegisterService {

    @Autowired
    private RegistrationRepository repository;


    public void saveRegistRecord(String registerName, String mailAddress, String phoneNumber, String address) {
        Register register = new Register();
        register.setId(0); // 設定 ID 為 0，讓 JPA 自動生成
        register.setRegisterName(registerName);
        register.setMailAddress(mailAddress);
        register.setPhoneNum(phoneNumber);
        register.setMailAddress(address);
        repository.save(register);
    }

    public RegisterService(RegistrationRepository repository) {
        this.repository = repository;
    }

    // public Register getId(Integer registerId) {
    //     return repository.getId(registerId);
    // }

    // 存儲報名資料
    public Register saveRegister(Register register) {
        return repository.save(register);
    }

    // 透過 email 查詢報名者
    public List<Register> getRegisterByEmail(String email) {
        return repository.findByMailAddress(email);
    }

    // 取得所有報名資料（管理員用）
    public List<Register> findAllList() {
        return repository.findAll();
    }

    public Register creaRegistRecord (Register register) {
        return repository.save(register);
    }

    public Register getById(Integer registerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Register> findByStudyId(Integer studyId) {
        return repository.findByStudies_Id(studyId);
    }
}