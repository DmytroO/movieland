package com.doliinyk.movieland.dao;

import com.doliinyk.movieland.entity.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getCountryByMovie(int movieId);
}
