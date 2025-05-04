// StudyRepository.java
package com.example.narrative.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.narrative.entity.Studies;

@Repository
public interface StudyRepository extends JpaRepository <Studies, Integer> {

    // 根據名稱模糊查詢
    List<Studies> findByNameContaining(String name);

    // 自定義 SQL（JPQL）查詢範例
    @Query("SELECT s FROM Studies s WHERE s.deadline < ?1")
    List<Studies> findExpiredStudies(LocalDate date);

    List<Studies> findAllByOrderByIdDesc(); // 根據ID降冪排序查詢所有讀書會


}
