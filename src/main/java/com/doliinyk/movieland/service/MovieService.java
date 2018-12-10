package com.doliinyk.movieland.service;

import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll(MovieRequestParameter movieRequestParameter);
    List<Movie> getThreeRandom();
    List<Movie> getByGenre(int id, MovieRequestParameter movieRequestParameter);
    Movie getById(int id, MovieRequestParameter movieRequestParameter);
}
