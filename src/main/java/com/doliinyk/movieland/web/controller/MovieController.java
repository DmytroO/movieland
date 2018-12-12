package com.doliinyk.movieland.web.controller;

import com.doliinyk.movieland.dao.common.Currency;
import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.dao.common.OrderType;
import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MovieController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private MovieService movieService;

    @Autowired
    @SuppressWarnings("unused")
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/v1/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll(
            @RequestParam(name = "price", required = false) OrderType priceOrder,
            @RequestParam(name = "rating", required = false) OrderType ratingOrder
    ) {
        MovieRequestParameter movieRequestParameter = new MovieRequestParameter();
        movieRequestParameter.addMovieOrderParameter("price", priceOrder);
        movieRequestParameter.addMovieOrderParameter("rating", ratingOrder);
        List<Movie> movies = movieService.getAll(movieRequestParameter);
        logger.info("getAll; movies.size={}", movies.size());
        return movies;
    }

    @GetMapping(path = "/v1/movie/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("unused")
    public List<Movie> getThreeRandom() {
        return movieService.getThreeRandom();
    }

    @GetMapping(
            path = "/v1/movie/genre/{genreId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @SuppressWarnings("unused")
    public List<Movie> getMovieByGenre(
            @PathVariable("genreId") int id,
            @RequestParam(name = "price", required = false) OrderType priceOrder,
            @RequestParam(name = "rating", required = false) OrderType ratingOrder
    ) {
        MovieRequestParameter movieRequestParameter = new MovieRequestParameter();
        movieRequestParameter.addMovieOrderParameter("price", priceOrder);
        movieRequestParameter.addMovieOrderParameter("rating", ratingOrder);
        return movieService.getByGenre(id, movieRequestParameter);
    }

    @GetMapping(path = "/v1/movie/{movieId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("unused")
    public Movie getMovieById(
            @PathVariable("movieId") int id,
            @RequestParam(name = "currency", required = false) Currency currency
    ) {
        MovieRequestParameter requestParameter = new MovieRequestParameter();
        requestParameter.setCurrency(currency);
        return movieService.getById(id, requestParameter);
    }
}
