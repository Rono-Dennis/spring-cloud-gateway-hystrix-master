package com.example.orderservice.repository;


import com.example.orderservice.entity.Genres;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genres, Integer> {

}
