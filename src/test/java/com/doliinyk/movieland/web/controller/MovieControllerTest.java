package com.doliinyk.movieland.web.controller;

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
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void testGetAll() throws Exception {
        List<Movie> movies = new ArrayList<>();
        Movie m = new Movie();
        m.setId(1);
        m.setNameRussian("Фильм1");
        m.setNameNative("Movie1");
        m.setYearOfRelease(1999);
        m.setPicturePath("some_directory/some_file.jpeg");
        m.setRating(9.00);
        m.setPrice(999.99);
        movies.add(m);

        when(movieServiceMock.getAll()).thenReturn(movies);

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
}
