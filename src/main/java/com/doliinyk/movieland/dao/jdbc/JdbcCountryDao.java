package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.CountryDao;
import com.doliinyk.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.doliinyk.movieland.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JdbcCountryDao implements CountryDao {
    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();
    private static final String GET_COUNTRIES_BY_MOVIE = "select c.id ,c.name\n" +
            "from       movieland_movie_country    mc\n" +
            "inner join movieland_country          c   on   c.id = mc.country_id\n" +
            "where   mc.movie_id = ?\n" +
            "order by c.id";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private JdbcTemplate jdbcTemplate;

    public JdbcCountryDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Country> getCountryByMovie(int movieId) {
        List<Country> countries = jdbcTemplate.query(GET_COUNTRIES_BY_MOVIE, COUNTRY_ROW_MAPPER, movieId);
        log.trace("countries {}", countries);
        return countries;
    }
}
