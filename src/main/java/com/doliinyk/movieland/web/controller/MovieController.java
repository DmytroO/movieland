package com.doliinyk.movieland.web.controller;

import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.dao.common.OrderType;
import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @SuppressWarnings("unused")
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

//    @RequestMapping(path = "/v1/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<Movie> getAll() {
//        return movieService.getAll(new MovieRequestParameter());
//    }

    @RequestMapping(path = "/v1/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll(
            @RequestParam(name = "price", required = false) OrderType priceOrder,
            @RequestParam(name = "rating", required = false) OrderType ratingOrder
    ) {
//        logger.info("getAll; price={}, rating={}", priceOrder.getOrderTypeName(), ratingOrder.getOrderTypeName());
        MovieRequestParameter movieRequestParameter = new MovieRequestParameter();
        movieRequestParameter.addMovieOrderParameter("price", priceOrder);
        movieRequestParameter.addMovieOrderParameter("rating", ratingOrder);
        List<Movie> movies = movieService.getAll(movieRequestParameter);
        logger.info("getAll; movies.size={}", movies.size());
        return movies;
    }

    @RequestMapping(path = "/v1/movie/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("unused")
    public List<Movie> getThreeRandom() {
        return movieService.getThreeRandom();
    }

    @RequestMapping(path = "/v1/movie/genre/{genreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @ExceptionHandler({IllegalArgumentException.class,NoSuchFieldError.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void handleBadRequests(Exception e) {
        logger.error("Exception:{}", e);
    }

}
