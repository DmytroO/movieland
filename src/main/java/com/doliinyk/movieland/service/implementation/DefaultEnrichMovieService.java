package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.CountryService;
import com.doliinyk.movieland.service.EnrichMovieService;
import com.doliinyk.movieland.service.GenreService;
import com.doliinyk.movieland.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class DefaultEnrichMovieService implements EnrichMovieService {
    private GenreService genreService;
    private ReviewService reviewService;
    private CountryService countryService;

    public DefaultEnrichMovieService(GenreService genreService, ReviewService reviewService, CountryService countryService) {
        this.genreService = genreService;
        this.reviewService = reviewService;
        this.countryService = countryService;
    }

    public void enrich(Movie movie) {
        int movieId = movie.getId();
        movie.setCountries(countryService.getCountryByMovie(movieId));
        movie.setGenres(genreService.getGenreByMovieId(movieId));
        movie.setReviews(reviewService.getReviewsByMovieId(movieId));
    }
}
