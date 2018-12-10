package com.doliinyk.movieland.dao.jdbc.mapper;

import com.doliinyk.movieland.entity.Review;
import com.doliinyk.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("nickname"),
                resultSet.getString("text")
        );
        log.trace("Review {}", review);
        return review;
    }
}
