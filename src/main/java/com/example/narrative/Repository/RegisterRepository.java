package com.example.narrative.Repository;

import org.springframework.stereotype.Repository;

import com.example.narrative.model.Register;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

//Repository層（(Dao層)
@Repository
public class RegisterRepository {
    // 這行是用來查詢註冊者名稱
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    // 這行是用來儲存註冊者資料
    public void save(Register register) {
        entityManager.persist(register);
    }

    // 這行是用來查詢註冊者
    public Register findById(Long id) {
        return entityManager.find(Register.class, id);
    }
}
