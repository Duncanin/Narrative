package com.example.narrative.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Studies {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate date;
    private String location;
    private LocalDate deadline;
    private int quota;
    private boolean isFull;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Register> registrations = new ArrayList<>();
}
