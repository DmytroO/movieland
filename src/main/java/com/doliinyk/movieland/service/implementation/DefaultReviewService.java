package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.ReviewDao;
import com.doliinyk.movieland.entity.Review;
import com.doliinyk.movieland.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReviewService implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private ReviewDao reviewDao;

    public DefaultReviewService(ReviewDao reviewDao) { this.reviewDao = reviewDao; }

    @Override
    public List<Review> getReviewsByMovieId(int movieId) {
        List<Review> reviews = reviewDao.getReviewByMovie(movieId);
        log.trace("reviews {}", reviews);
        log.info("reviews.size {}", reviews.size());
        return reviews;
    }
}
