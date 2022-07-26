package com.example.orderservice.service;

import com.example.orderservice.entity.Genres;
import com.example.orderservice.entity.Languages;
import com.example.orderservice.entity.Movies;
import com.example.orderservice.entity.Reviews;
import com.example.orderservice.repository.LanguageRepository;
import com.example.orderservice.repository.MoviesRepository;
import com.example.orderservice.repository.RatingsRepository;
import com.example.orderservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoviesService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public void addMovie(Movies movie) {
        movie.setCreatedTimestamp(new Date());
        movie.setLastUpdtTimestamp(new Date());
        movie.setRating(null);
        moviesRepository.save(movie);
    }

    public List<Movies> getAllMovies() {
        List<Movies> list = (List<Movies>) moviesRepository.findAll();
        updateLanguageGenres(list);
        return list;
    }


    public List<Movies> getMovieInfo(Integer movieId) {

        Optional<Movies> movieOptional = moviesRepository.findById(movieId);
        Movies movie = movieOptional.get();
        // Update Reviews
        List<Reviews> reviews = reviewRepository.fetchReviewByMovieId(movie.getId());
        movie.setReviews(reviews);

        // Update Genre & Language
        List<Movies> list = Arrays.asList(new Movies[] { movie });
        updateLanguageGenres(list);

        return list;
    }


    private void updateLanguageGenres(List<Movies> list) {
        Map<Integer, String> languageMap = new HashMap<>();
        Map<Integer, String> genreMap = new HashMap<>();

        List<Languages> languageList = languageService.getAllLanguages();
        languageList.parallelStream().forEach(obj -> languageMap.put(obj.getId(), obj.getName()));

        List<Genres> genreList = genreService.getAllGenres();
        genreList.parallelStream().forEach(obj -> genreMap.put(obj.getId(), obj.getName()));

        list.parallelStream().forEach(obj -> {
            obj.setLanguage(languageMap.get(obj.getLanguageId()));
            obj.setGenre(genreMap.get(obj.getGenreId()));
        });

    }


}
