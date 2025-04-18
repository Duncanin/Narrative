package com.example.narrative.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.narrative.entity.CardMedium;
import com.example.narrative.repository.CardMediumRepository;

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
