package com.example.orderservice.controller;

import com.example.orderservice.common.Payment;
import com.example.orderservice.common.TransactionRequest;
import com.example.orderservice.common.TransactionResponse;
import com.example.orderservice.entity.*;
import com.example.orderservice.repository.LanguageRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService service;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ReviewService reviewService;

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);


    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {

        return service.saveOrder(transactionRequest);
    }

    @PostMapping("/bookLanguage")
    public Languages bookLanguage(@RequestBody Languages languages) throws JsonProcessingException {

        return languageService.saveLanguage(languages);
    }

    @GetMapping("/bookedLanguages")
    public List<Languages> getAllLanguages() {
        return (List<Languages>) languageService.getAllLanguages();
    }

    @GetMapping("/bookedOrders")
    public List<Order> getAllOrders() {
        return  orderRepository.findAll();
    }

    @GetMapping("/genres")
    public List<Genres> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/popular")
    public List<Movies> getPopularMovies() {
        LOG.info("Fetch Popular Movies...");
        List<Movies> list = moviesService.getAllMovies();
        LOG.debug(": " + list.size());
        List<Movies> ratedMovies = list.parallelStream().filter(obj -> null != obj.getRating())
                .collect(Collectors.toList());
        LOG.debug(": " + ratedMovies.size());
        return ratedMovies;
    }

    @GetMapping("/{movieId}")
    public List<Movies> getMovieInfo(@PathVariable("movieId") Integer movieId) {
        return moviesService.getMovieInfo(movieId);
    }

    @PostMapping("/review")
    public List<Movies> addMovieReview(@RequestBody Reviews reviews) {
        LOG.info("Add Movie Reviews...");
        reviewService.addReview(reviews);

        return moviesService.getMovieInfo(reviews.getMovieId());
    }

    @RequestMapping(value = "FetchMovies",method = RequestMethod.GET)
    public List<Movies> getAllMovies() {
        LOG.info("Fetch all the Movies...");
        return moviesService.getAllMovies();
    }

    @RequestMapping(value = "AddMovie",method = RequestMethod.POST)//"created_timestamp":"1658840058",
    public List<Movies> addMovie(@RequestBody Movies movie) {// "last_updt_timestamp":"1658840058",
        LOG.info("Add a Movie...");
        moviesService.addMovie(movie);
        return moviesService.getAllMovies();
    }



}
