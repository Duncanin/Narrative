package com.example.narrative.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.narrative.entity.StudyFormField;

@Repository
public interface StudyFormFieldRepository extends JpaRepository<StudyFormField, Integer> {
    List<StudyFormField> findByStudies_Id(Integer studyId);
    void deleteByStudies_Id(Integer studyId);
}
