package com.example.orderservice.repository;

import com.example.orderservice.entity.Reviews;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewRepository extends CrudRepository<Reviews, Integer> {
	
	@Query("Select reviews from Reviews reviews where reviews.movieId= :movieId")
	List<Reviews> fetchReviewByMovieId(@Param("movieId") Integer movieId);

}
