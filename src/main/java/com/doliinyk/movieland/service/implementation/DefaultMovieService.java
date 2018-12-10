package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultEnrichMovieService defaultEnrichMovieService;

    public DefaultMovieService(MovieDao movieDao, DefaultEnrichMovieService defaultEnrichMovieService) {
        this.movieDao = movieDao;
        this.defaultEnrichMovieService = defaultEnrichMovieService;
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
        defaultEnrichMovieService.enrich(movie);
        logger.trace("movie: {}", movie);
        return movie;
    }

}
