package com.doliinyk.movieland.service;

import com.doliinyk.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    List<Movie> getThreeRandom();
}
