// StudyService.java
package com.example.narrative.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.narrative.entity.Studies;
import com.example.narrative.repository.StudyRepository;

@Service
public class StudyService {
    
    @Autowired
    private StudyRepository studyRepository;

    public void saveStudyRecord(String name, LocalDate date, String location, LocalDate deadline, int quota) {
        Studies study = new Studies();
        study.setName(name);
        study.setDate(date);
        study.setLocation(location);
        study.setDeadline(deadline);
        study.setQuota(quota);
        studyRepository.save(study);
    }

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
            study.setExpired(study.getDeadline() != null && LocalDate.now().isAfter(study.getDeadline())); // 判斷是否過期
            int remainingQuota = study.getQuota() - study.getRegistrations().size();
            study.setRemainingQuota(remainingQuota);
        }
        return studies;
    }

    public Studies findById(int id) {
        return studyRepository.findById(id).orElse(null);
    }

    //程式邏輯運算剩餘名額
    public List<Studies> findAllNotFull() {
        List<Studies> all = studyRepository.findAll();
        return all.stream()
                .filter(study -> study.getRegistrations().size() < study.getQuota())
                .toList();
    }

}
