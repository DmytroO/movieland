package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.CurrencyService;
import com.doliinyk.movieland.service.EnrichMovieService;
import com.doliinyk.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private EnrichMovieService enrichMovieService;
    private CurrencyService currencyService;

    public DefaultMovieService(
            MovieDao movieDao,
            EnrichMovieService enrichMovieService,
            CurrencyService currencyService
    ) {
        this.movieDao = movieDao;
        this.enrichMovieService = enrichMovieService;
        this.currencyService = currencyService;
    }

    @Override
    public List<Movie> getAll(MovieRequestParameter movieRequestParameter) {
        List<Movie> movies = movieDao.getAll(movieRequestParameter);
        logger.trace("movies {}", movies);
        logger.info("movies.size {}", movies.size());
        return movies;
    }

    @Override
    public List<Movie> getThreeRandom() {
        List<Movie> movies = movieDao.getThreeRandom();
        logger.trace("movies {}" ,movies);
        logger.info("movies.size {}" ,movies.size());
        return movies;
    }

    @Override
    public List<Movie> getByGenre(int id, MovieRequestParameter movieRequestParameter) {
        List<Movie> movies = movieDao.getByGenre(id, movieRequestParameter);
        logger.trace("genre: {}, movies: {}", id, movies);
        logger.info("genre: {}, movies.size: {}", id, movies.size());
        return movies;
    }

    @Override
    public Movie getById(int id, MovieRequestParameter movieRequestParameter) {
        Movie movie = movieDao.getById(id, movieRequestParameter);
        enrichMovieService.enrich(movie);
        if (movieRequestParameter.getCurrency() != null)
            movie.setPrice(currencyService.convertToCurrency(movie.getPrice() ,movieRequestParameter.getCurrency()));
        logger.info("movie: {}, currency: {}, price: {}", movie, movieRequestParameter.getCurrency());
        return movie;
    }

}
