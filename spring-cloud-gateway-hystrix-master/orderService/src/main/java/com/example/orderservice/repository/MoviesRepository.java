package com.example.orderservice.repository;


import com.example.orderservice.entity.Movies;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movies, Integer> {

}
