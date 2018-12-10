package com.doliinyk.movieland.service;

import com.doliinyk.movieland.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByMovieId(int movieId);
}
