package com.example.orderservice.repository;

import com.example.orderservice.entity.Ratings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RatingsRepository extends CrudRepository<Ratings, Integer> {
	
	@Query("Select rating from Ratings rating where rating.movieId= :movieId")
	List<Ratings> fetchRatingByMovieId(@Param("movieId") Integer movieId);

}
