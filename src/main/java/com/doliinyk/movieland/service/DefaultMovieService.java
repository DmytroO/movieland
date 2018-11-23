package com.doliinyk.movieland.service;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public DefaultMovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = movieDao.getAll();
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
    public List<Movie> getByGenre(int id) {
        List<Movie> movies = movieDao.getByGenre(id);
        logger.trace("genre: {}, movies: {}", id, movies);
        logger.info("genre: {}, movies.size: {}", id, movies.size());
        return movies;
    }
}
