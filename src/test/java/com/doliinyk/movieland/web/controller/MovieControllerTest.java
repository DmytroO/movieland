package com.doliinyk.movieland.web.controller;

import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.entity.Movie;
import com.doliinyk.movieland.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    MovieController movieController;

    @Mock
    MovieService movieServiceMock;

    @Before
    public void setUp() { mockMvc = MockMvcBuilders.standaloneSetup(movieController).build(); }

    @Test
    public void getAll() throws Exception {
        List<Movie> movies = new ArrayList<>();
        Movie m = new Movie();
        m.setId(1);
        m.setNameRussian("Фильм1");
        m.setNameNative("Movie1");
        m.setYearOfRelease(1999);
        m.setPicturePath("some_directory/some_file.jpeg");
        m.setRating(9.00);
        m.setPrice(999.99);
        m.setPicturePath("picture.png");
        movies.add(m);

        when(movieServiceMock.getAll(eq(null))).thenReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameRussian", is("Фильм1")))
                .andExpect(jsonPath("$[0].nameNative" ,is("Movie1")))
                .andExpect(jsonPath("$[0].rating" ,is(9.00)))
                .andExpect(jsonPath("$[0].price" ,is(999.99)));
    }

    @Test
    public void getThreeRandom() throws Exception {
        List<Movie> movies = new ArrayList<>();
        Movie m1 = new Movie();
        m1.setId(1);
        m1.setNameRussian("Фильм1");
        m1.setNameNative("Movie1");
        m1.setYearOfRelease(1999);
        m1.setPicturePath("movie1_picture_path.jpeg");
        m1.setRating(9.00);
        m1.setPrice(999.99);
        movies.add(m1);

        Movie m2 = new Movie();
        m2.setId(2);
        m2.setNameRussian("Фильм2");
        m2.setNameNative("Movie2");
        m2.setYearOfRelease(1999);
        m2.setPicturePath("movie2_picture_path.jpg");
        m2.setRating(9.9);
        m2.setPrice(99.00);
        movies.add(m2);

        Movie m3 = new Movie();
        m3.setId(3);
        m3.setNameRussian("Фильм3");
        m3.setNameNative("Movie3");
        m3.setYearOfRelease(2001);
        m3.setPicturePath("movie3_picture_path.jpg");
        m3.setRating(7.9);
        m3.setPrice(0.01);
        movies.add(m3);

        when(movieServiceMock.getThreeRandom()).thenReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/movie/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameRussian", is("Фильм1")))
                .andExpect(jsonPath("$[0].nameNative" ,is("Movie1")))
                .andExpect(jsonPath("$[0].picturePath" ,is("movie1_picture_path.jpeg")))
                .andExpect(jsonPath("$[0].rating" ,is(9.00)))
                .andExpect(jsonPath("$[0].price" ,is(999.99)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nameRussian", is("Фильм2")))
                .andExpect(jsonPath("$[1].nameNative" ,is("Movie2")))
                .andExpect(jsonPath("$[1].picturePath" ,is("movie2_picture_path.jpg")))
                .andExpect(jsonPath("$[1].rating" ,is(9.9)))
                .andExpect(jsonPath("$[1].price" ,is(99.00)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].nameRussian", is("Фильм3")))
                .andExpect(jsonPath("$[2].nameNative" ,is("Movie3")))
                .andExpect(jsonPath("$[2].picturePath" ,is("movie3_picture_path.jpg")))
                .andExpect(jsonPath("$[2].rating" ,is(7.9)))
                .andExpect(jsonPath("$[2].price" ,is(0.01)));
    }

    @Test
    public void getByGenre() throws Exception {
        List<Movie> movies = new ArrayList<>();
        Movie m1 = new Movie();
        m1.setId(1);
        m1.setNameRussian("Фильм1");
        m1.setNameNative("Movie1");
        m1.setYearOfRelease(1999);
        m1.setPicturePath("movie1_picture_path.jpeg");
        m1.setRating(9.00);
        m1.setPrice(999.99);
        movies.add(m1);

        Movie m2 = new Movie();
        m2.setId(2);
        m2.setNameRussian("Фильм2");
        m2.setNameNative("Movie2");
        m2.setYearOfRelease(1999);
        m2.setPicturePath("movie2_picture_path.jpg");
        m2.setRating(9.9);
        m2.setPrice(99.00);
        movies.add(m2);

        Movie m3 = new Movie();
        m3.setId(3);
        m3.setNameRussian("Фильм3");
        m3.setNameNative("Movie3");
        m3.setYearOfRelease(2001);
        m3.setPicturePath("movie3_picture_path.jpg");
        m3.setRating(7.9);
        m3.setPrice(0.01);
        movies.add(m3);

        when(movieServiceMock.getByGenre(eq(1), eq(null))).thenReturn(movies);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/movie/genre/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameRussian", is("Фильм1")))
                .andExpect(jsonPath("$[0].nameNative" ,is("Movie1")))
                .andExpect(jsonPath("$[0].picturePath" ,is("movie1_picture_path.jpeg")))
                .andExpect(jsonPath("$[0].rating" ,is(9.00)))
                .andExpect(jsonPath("$[0].price" ,is(999.99)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nameRussian", is("Фильм2")))
                .andExpect(jsonPath("$[1].nameNative" ,is("Movie2")))
                .andExpect(jsonPath("$[1].picturePath" ,is("movie2_picture_path.jpg")))
                .andExpect(jsonPath("$[1].rating" ,is(9.9)))
                .andExpect(jsonPath("$[1].price" ,is(99.00)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].nameRussian", is("Фильм3")))
                .andExpect(jsonPath("$[2].nameNative" ,is("Movie3")))
                .andExpect(jsonPath("$[2].picturePath" ,is("movie3_picture_path.jpg")))
                .andExpect(jsonPath("$[2].rating" ,is(7.9)))
                .andExpect(jsonPath("$[2].price" ,is(0.01)));
    }
}
