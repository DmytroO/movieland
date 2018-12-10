package com.doliinyk.movieland.web.controller;

import com.doliinyk.movieland.dao.ReviewDao;
import com.doliinyk.movieland.entity.Review;
import com.doliinyk.movieland.service.implementation.DefaultReviewService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {
    @Test
    public void getReviewByMovieIdTest() {
        ReviewDao mockReviewDao = mock(ReviewDao.class);
        when(mockReviewDao.getReviewByMovie(1)).thenReturn(getReviewsByMovieIdTest());

        DefaultReviewService defaultReviewService = new DefaultReviewService(mockReviewDao);

        defaultReviewService.getReviewsByMovieId(1);
        assertEquals(getReviewsByMovieIdTest(), defaultReviewService.getReviewsByMovieId(1));

    }

    private List<Review> getReviewsByMovieIdTest() {
        Review review1 = new Review(1, 1, "nickname", "is's a review");
        Review review2 = new Review(2, 2, "nickname", "is's a review");
        return Arrays.asList(review1, review2);
    }
}
