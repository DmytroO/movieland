package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.CountryService;
import com.doliinyk.movieland.service.GenreService;
import com.doliinyk.movieland.service.MovieService;
import com.doliinyk.movieland.service.ReviewService;
import com.doliinyk.movieland.service.enrich.EnrichMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private EnrichMovie enrichMovie;

    public DefaultMovieService(MovieDao movieDao, EnrichMovie enrichMovie) {
        this.movieDao = movieDao;
        this.enrichMovie = enrichMovie;
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
        enrichMovie.enrich(movie);
        logger.trace("movie: {}", movie);
        return movie;
    }

}
