package com.doliinyk.movieland.service;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.entity.Movie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultMovieServiceTest {
    @Test
    public void testGetAll() {
        MovieDao movieDao = mock(MovieDao.class);
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

        when(movieDao.getAll()).thenReturn(expectedMovies);

        DefaultMovieService defaultMovieService = new DefaultMovieService(movieDao);
        List<Movie> actualMovies = defaultMovieService.getAll();

        assertEquals(2 ,actualMovies.size());
        for (int i = expectedMovies.size() - 1; i >= 0; i--) {
            assertTrue(actualMovies.remove(expectedMovies.get(i)));
        }
        assertEquals(0 ,actualMovies.size());
    }
}
