package com.doliinyk.movieland.dao.jdbc.mapper;

import com.doliinyk.movieland.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country(resultSet.getInt("id"), resultSet.getString("name"));
        log.trace("country {}", country);
        return country;
    }
}
