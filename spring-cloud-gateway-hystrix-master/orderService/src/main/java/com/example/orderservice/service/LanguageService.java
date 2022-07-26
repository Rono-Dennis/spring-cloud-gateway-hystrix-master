package com.example.orderservice.service;

import com.example.orderservice.entity.Languages;
import com.example.orderservice.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Languages saveLanguage(Languages languages){
        return languageRepository.save(languages);
    }

    public List<Languages> getAllLanguages() {
        return (List<Languages>) languageRepository.findAll();
    }
}
