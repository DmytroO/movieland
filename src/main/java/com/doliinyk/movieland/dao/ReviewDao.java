package com.doliinyk.movieland.dao;

import com.doliinyk.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getReviewByMovie(int movieId);
}
