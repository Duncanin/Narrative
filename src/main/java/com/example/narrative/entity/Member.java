package com.example.narrative.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "member", // 資料表名稱
        catalog = "narrative_management") // 資料庫名稱
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;
    
    // @ManyToOne // 報名者可以報名多個讀書會
    // @JoinTable(name = "studies_id")
    // private Studies studies;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    // @OneToMany(mappedBy = "member")
    // private List<RegistRecord> registRecords;

 // 無參數建構子 (JPA 需要)
    public Member() {
    }

    //Entity不可放入Repository，會被Java認為是一個巢狀類別(Inner class) 導致整個類別無法正常解析getter/setter
    // public interface MemberRepository extends JpaRepository<Member, Long> {
    //     // 這裡可以添加自定義查詢方法
    // }


}
