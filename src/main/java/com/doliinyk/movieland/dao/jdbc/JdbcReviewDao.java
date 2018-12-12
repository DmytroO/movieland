package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.ReviewDao;
import com.doliinyk.movieland.dao.jdbc.mapper.ReviewRowMapper;
import com.doliinyk.movieland.entity.Review;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcReviewDao implements ReviewDao {
    private static final ReviewRowMapper REVIEW_ROW_MAPPER = new ReviewRowMapper();
    private JdbcTemplate jdbcTemplate;
    private static final String REVIEW_BY_MOVIE = "select r.id ,r.user_id ,u.username as nickname ,r.review as text\n" +
            "from       movieland_review    r\n" +
            "inner join movieland_user      u  on u.id = r.user_id\n" +
            "where  r.movie_id = ?\n" +
            "order by id";

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Review> getReviewByMovie(int movieId) {
        return jdbcTemplate.query(REVIEW_BY_MOVIE, REVIEW_ROW_MAPPER, movieId);
    }
}
