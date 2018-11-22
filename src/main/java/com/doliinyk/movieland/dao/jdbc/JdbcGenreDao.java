package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.GenreDao;
import com.doliinyk.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.doliinyk.movieland.entity.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreDao implements GenreDao {
    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();
    private JdbcTemplate jdbcTemplate;
    private static String GET_ALL_GENRE = "select g.id ,g.name from movieland_genre g order by g.name";

    public JdbcGenreDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Genre> getAll() { return jdbcTemplate.query(GET_ALL_GENRE, GENRE_ROW_MAPPER); }

}
