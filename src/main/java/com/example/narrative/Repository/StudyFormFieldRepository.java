package com.example.narrative.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.narrative.model.StudyFormField;

@Repository
public interface StudyFormFieldRepository extends JpaRepository<StudyFormField, Long> {
    List<StudyFormField> findByStudies_Id(Integer studyId);
    void deleteByStudies_Id(Integer studyId);
}
