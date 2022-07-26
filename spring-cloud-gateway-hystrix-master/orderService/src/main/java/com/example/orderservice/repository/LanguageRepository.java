package com.example.orderservice.repository;


import com.example.orderservice.entity.Languages;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Languages, Integer> {

}
