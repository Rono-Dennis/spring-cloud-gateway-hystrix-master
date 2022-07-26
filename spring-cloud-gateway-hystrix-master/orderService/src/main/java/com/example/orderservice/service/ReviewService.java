package com.example.orderservice.service;

import com.example.orderservice.entity.Ratings;
import com.example.orderservice.entity.Reviews;
import com.example.orderservice.repository.RatingsRepository;
import com.example.orderservice.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RatingsRepository ratingsRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ReviewService.class);

    public void addReview(Reviews reviews) {
        reviews.setCreateTimestamp(new Date());
        reviewRepository.save(reviews);
        Ratings ratings = null;
        List<Ratings> ratingList = ratingsRepository.fetchRatingByMovieId(reviews.getMovieId());
        if (!ratingList.isEmpty()) {
            LOG.info("Update existing ratings...");
            ratings = ratingList.get(0);
        } else {
            LOG.info("Add a new ratings...");
            ratings = new Ratings(reviews.getMovieId(), 0, 0, (double) 0, 0, new Date());
        }

        if ("Y".equalsIgnoreCase(reviews.getLikeMovie())) {
            ratings.setLikes(ratings.getLikes() + 1);
        } else {
            ratings.setDislike(ratings.getDislike() + 1);
        }
        ratings.setTotalRatings(ratings.getTotalRatings() + 1);
        ratingsRepository.save(ratings);
    }

}
