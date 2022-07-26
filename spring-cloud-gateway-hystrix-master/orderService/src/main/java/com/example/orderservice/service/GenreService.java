package com.example.orderservice.service;

import com.example.orderservice.entity.Genres;
import com.example.orderservice.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<Genres> getAllGenres() {
        return (List<Genres>) genreRepository.findAll();
    }

}
