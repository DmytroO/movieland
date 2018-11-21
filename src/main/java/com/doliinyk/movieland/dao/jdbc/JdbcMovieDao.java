package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.doliinyk.movieland.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_MOVIE = "select m.id ,m.name_russian as nameRussian ,m.name_native as nameNative" +
            " ,m.year_of_release as yearOfRelease ,m.rating ,m.price ,p.poster_url as picturePath" +
            " from       movieland_movie  m " +
            " inner join movieland_poster p on p.movie_id = m.id" +
            " order by m.id";

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll() {
        return jdbcTemplate.query(GET_ALL_MOVIE, MOVIE_ROW_MAPPER);
    }
}
