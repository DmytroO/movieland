package com.doliinyk.movieland.service;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.implementation.DefaultEnrichMovieService;
import com.doliinyk.movieland.service.implementation.DefaultMovieService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultMovieServiceTest {
    @Test
    public void getAll() {
        MovieDao movieDao = mock(MovieDao.class);
        DefaultEnrichMovieService defaultEnrichMovieService = mock(DefaultEnrichMovieService.class);
        List<Movie> expectedMovies = new ArrayList<>();

        Movie m1 = new Movie();
        m1.setId(1);
        m1.setNameRussian("Фильм1");
        m1.setNameNative("Movie1");
        m1.setYearOfRelease(2000);
        m1.setPicturePath("movie1_picture_path.jpeg");
        m1.setRating(9.0);
        m1.setPrice(999.99);
        expectedMovies.add(m1);

        Movie m2 = new Movie();
        m2.setId(2);
        m2.setNameRussian("Фильм2");
        m2.setNameNative("Movie2");
        m2.setYearOfRelease(1999);
        m2.setPicturePath("movie2_picture_path.jpg");
        m2.setRating(9.9);
        m2.setPrice(99.00);
        expectedMovies.add(m2);

        when(movieDao.getAll(eq(null))).thenReturn(new ArrayList<>(expectedMovies));

        DefaultMovieService defaultMovieService = new DefaultMovieService(movieDao, defaultEnrichMovieService);
        List<Movie> actualMovies = defaultMovieService.getAll(null);

        assertEquals(2 ,actualMovies.size());
        for (int i = 0; i < expectedMovies.size(); i++) {
            assertTrue(actualMovies.remove(expectedMovies.get(i)));
        }
        assertEquals(0 ,actualMovies.size());
    }

    @Test
    public void getThreeRandom() {
        MovieDao movieDao = mock(MovieDao.class);
        DefaultEnrichMovieService defaultEnrichMovieService = mock(DefaultEnrichMovieService.class);
        List<Movie> expectedMovies = new ArrayList<>();

        Movie m1 = new Movie();
        m1.setId(1);
        m1.setNameRussian("Фильм1");
        m1.setNameNative("Movie1");
        m1.setYearOfRelease(2000);
        m1.setPicturePath("movie1_picture_path.jpeg");
        m1.setRating(9.0);
        m1.setPrice(999.99);
        expectedMovies.add(m1);

        Movie m2 = new Movie();
        m2.setId(2);
        m2.setNameRussian("Фильм2");
        m2.setNameNative("Movie2");
        m2.setYearOfRelease(1999);
        m2.setPicturePath("movie2_picture_path.jpg");
        m2.setRating(9.9);
        m2.setPrice(99.00);
        expectedMovies.add(m2);

        Movie m3 = new Movie();
        m3.setId(3);
        m3.setNameRussian("Фильм3");
        m3.setNameNative("Movie3");
        m3.setYearOfRelease(2001);
        m3.setPicturePath("movie3_picture_path.jpg");
        m3.setRating(7.9);
        m3.setPrice(0.01);
        expectedMovies.add(m3);

        when(movieDao.getThreeRandom()).thenReturn(new ArrayList<>(expectedMovies));

        DefaultMovieService defaultMovieService = new DefaultMovieService(movieDao, defaultEnrichMovieService);
        List<Movie> actualMovies = defaultMovieService.getThreeRandom();

        assertEquals(3 ,actualMovies.size());
        for (int i = 0; i < expectedMovies.size(); i++) {
            assertTrue(actualMovies.remove(expectedMovies.get(i)));
        }
        assertEquals(0 ,actualMovies.size());
    }

    @Test
    public void getByGenre() {
        MovieDao movieDao = mock(MovieDao.class);
        DefaultEnrichMovieService defaultEnrichMovieService = mock(DefaultEnrichMovieService.class);
        List<Movie> expectedMovies = new ArrayList<>();

        Movie m1 = new Movie();
        m1.setId(1);
        m1.setNameRussian("Фильм1");
        m1.setNameNative("Movie1");
        m1.setYearOfRelease(2000);
        m1.setPicturePath("movie1_picture_path.jpeg");
        m1.setRating(9.0);
        m1.setPrice(999.99);
        expectedMovies.add(m1);

        Movie m2 = new Movie();
        m2.setId(2);
        m2.setNameRussian("Фильм2");
        m2.setNameNative("Movie2");
        m2.setYearOfRelease(1999);
        m2.setPicturePath("movie2_picture_path.jpg");
        m2.setRating(9.9);
        m2.setPrice(99.00);
        expectedMovies.add(m2);

        Movie m3 = new Movie();
        m3.setId(3);
        m3.setNameRussian("Фильм3");
        m3.setNameNative("Movie3");
        m3.setYearOfRelease(2001);
        m3.setPicturePath("movie3_picture_path.jpg");
        m3.setRating(7.9);
        m3.setPrice(0.01);
        expectedMovies.add(m3);

        when(movieDao.getByGenre(eq(1), eq(null))).thenReturn(new ArrayList<>(expectedMovies));

        DefaultMovieService defaultMovieService = new DefaultMovieService(movieDao, defaultEnrichMovieService);
        List<Movie> actualMovies = defaultMovieService.getByGenre(1, null);

        assertEquals(3 ,actualMovies.size());
        for (int i = 0; i < expectedMovies.size(); i++) {
            assertTrue(actualMovies.remove(expectedMovies.get(i)));
        }
        assertEquals(0 ,actualMovies.size());
    }

}
