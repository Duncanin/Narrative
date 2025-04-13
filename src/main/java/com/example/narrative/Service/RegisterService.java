package com.example.narrative.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.narrative.dao.RegisterDao;
import com.example.narrative.model.Register;
import com.example.narrative.Repository.RegistrationRepository;

@Service
public class RegisterService {

    // private final RegistrationRepository Repository;
    @Autowired
    private final RegisterDao resgisterDao;
    @Autowired
    private RegistrationRepository repository;


    public void saveRegistRecord(String registerName, String mailAddress, String phoneNumber, String address) {
        Register register = new Register();
        register.setRegisterName(registerName);
        register.setMailAddress(mailAddress);
        register.setPhoneNum(phoneNumber);
        register.setMailAddress(address);
        resgisterDao.save(register);
    }

    public RegisterService(RegisterDao resgisterDao) {
        this.resgisterDao = resgisterDao;
    }

    public Register getId(Integer registerId) {
        return resgisterDao.getId(registerId);
    }

    // 存儲報名資料
    public Register saveRegister(Register register) {
        return resgisterDao.save(register);
    }

    // 透過 email 查詢報名者
    public List<Register> getRegisterByEmail(String email) {
        return resgisterDao.findByMailAddress(email);
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
}