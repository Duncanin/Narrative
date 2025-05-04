// StudyService.java
package com.example.narrative.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.narrative.entity.Studies;
import com.example.narrative.repository.StudyRepository;

@Service
public class StudyService {
    
    final private StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public List <Studies> findAllStudies() {
        return studyRepository.findAll();
    }

    public void save(Studies study) {
        studyRepository.save(study);
    }

    public void delete(int id) {
        studyRepository.deleteById(id);
    }

    public List<Studies> findAll() {
        return studyRepository.findAll();
    }

    public List<Studies> findAllWithRemainingQuota() {
        List<Studies> studies = studyRepository.findAll();
        for (Studies study : studies) {
            study.setExpired(study.getDeadline() != null && LocalDateTime.now().isAfter(study.getDeadline())); // 判斷是否過期
            int remainingQuota = study.getQuota() - study.getRegistrations().size();
            study.setRemainingQuota(remainingQuota);
        }
        return studies;
    }

    public Studies findById(int id) {
        return studyRepository.findById(id).orElse(null);
    }

    // 查找可報名場次
    public List<Studies> findAvailableSessions() {
        return studyRepository.findAll().stream()
            .peek(study -> {
                int remainingQuota = study.getQuota() - study.getRegistrations().size();
                study.setRemainingQuota(remainingQuota);
                study.setExpired(study.getDeadline() != null && LocalDateTime.now().isAfter(study.getDeadline()));
            })
            .filter(study -> !study.isExpired() && study.getRemainingQuota() > 0)
            .toList();
    }
    

    public List<Studies> findAllByLatestFirst() {
        List<Studies> studies = studyRepository.findAllByOrderByIdDesc();
        for (Studies study : studies) {
            study.setExpired(study.getDeadline() != null && LocalDateTime.now().isAfter(study.getDeadline()));
            int remainingQuota = study.getQuota() - study.getRegistrations().size();
            study.setRemainingQuota(remainingQuota);
        }
        return studies;
    }
    
}
