package com.example.narrative.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.narrative.Repository.CardMediumRepository;
import com.example.narrative.model.CardMedium;

@Service
public class CardMediumService {
    private final CardMediumRepository repository;

    public CardMediumService (CardMediumRepository repository) {
        this.repository = repository;
    }

    public List<CardMedium> findAll() {
        return repository.findAll();
    }

    public CardMedium findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
