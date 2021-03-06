package com.doliinyk.movieland.dao.jdbc.mapper;

import com.doliinyk.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements  RowMapper<Movie> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setNameRussian(resultSet.getString("nameRussian"));
        movie.setNameNative(resultSet.getString("nameNative"));
        movie.setYearOfRelease(resultSet.getInt("yearOfRelease"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getDouble("price"));
        movie.setPicturePath(resultSet.getString("picturePath"));
        movie.setDescription(resultSet.getString("description"));
        logger.debug("Movie {}", movie);
        return movie;
    }
}
