package com.doliinyk.movieland.service;

import com.doliinyk.movieland.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> getCountryByMovie(int movie_id);
}
