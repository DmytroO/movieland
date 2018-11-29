package com.doliinyk.movieland.dao;

import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.entity.Movie;
import java.util.List;

public interface MovieDao {
    List<Movie> getAll(MovieRequestParameter movieRequestParameter);
    List<Movie> getThreeRandom();
    List<Movie> getByGenre(int id, MovieRequestParameter movieRequestParameter);
}
