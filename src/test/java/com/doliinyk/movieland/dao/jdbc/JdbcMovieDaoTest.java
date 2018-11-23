package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.doliinyk.movieland.entity.Movie;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcMovieDaoTest {
    @Test
    public void getAll() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
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

        when(jdbcTemplate.query(any(String.class), any(MovieRowMapper.class))).thenReturn(new ArrayList<>(expectedMovies));

        JdbcMovieDao jdbcMovieDao = new JdbcMovieDao(jdbcTemplate);
        List<Movie> actualMovies = jdbcMovieDao.getAll();

        assertEquals(2 ,actualMovies.size());
        for (int i = 0; i < expectedMovies.size(); i++) {
            assertTrue(actualMovies.remove(expectedMovies.get(i)));
        }
        assertEquals(0 ,actualMovies.size());
    }

    @Test
    public void getThreeRandom() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
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

        when(jdbcTemplate.query(any(String.class), any(MovieRowMapper.class))).thenReturn(new ArrayList<>(expectedMovies));

        JdbcMovieDao jdbcMovieDao = new JdbcMovieDao(jdbcTemplate);
        List<Movie> actualMovies = jdbcMovieDao.getThreeRandom();

        assertEquals(3 ,actualMovies.size());
        for (int i = 0; i < expectedMovies.size(); i++) {
            assertTrue(actualMovies.remove(expectedMovies.get(i)));
        }
        assertEquals(0 ,actualMovies.size());
    }

    @Test
    public void getByGenre() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
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

        when(jdbcTemplate.query(any(String.class), any(MovieRowMapper.class), eq(1))).thenReturn(new ArrayList<>(expectedMovies));

        JdbcMovieDao jdbcMovieDao = new JdbcMovieDao(jdbcTemplate);
        List<Movie> actualMovies = jdbcMovieDao.getByGenre(1);

        assertEquals(3 ,actualMovies.size());
        for (int i = 0; i < expectedMovies.size(); i++) {
            assertTrue(actualMovies.remove(expectedMovies.get(i)));
        }
        assertEquals(0 ,actualMovies.size());
    }
}
