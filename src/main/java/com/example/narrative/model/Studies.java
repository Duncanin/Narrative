// File: Studies.java
package com.example.narrative.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "studies", catalog = "narrative_management") // 資料表名稱
public class Studies {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "location")
    private String location;
    @Column(name = "deadline")
    private LocalDate deadline;
    @Column(name = "quota")
    private int quota;
    @Column(name = "is_full")
    private boolean isFull;

    @OneToMany(mappedBy = "studies", cascade = CascadeType.ALL)
    private List<Register> registrations = new ArrayList<>();

    @Transient
    private int remainingQuota;

    @Transient
    private Map<String, String> extraFields; // JSON 格式儲存欄位配置

    public Studies() {
    }

    public Studies(Integer id) {
        this.id = id;
    }

    public interface StudiesRecordRepository extends JpaRepository<Studies, Integer> {
        // JpaRepository 提供了許多 CRUD 操作的方法
        // 你可以在這裡添加自定義查詢方法
        List<Studies> findByNameContaining(String name);
        List<Studies> findByLocationContaining(String location);
        List<Studies> findByDate(LocalDate date);
        List<Studies> findByDeadline(LocalDate deadline);
        List<Studies> findByQuota(int quota);
        List<Studies> findByIsFull(boolean isFull);
    }

    
}
