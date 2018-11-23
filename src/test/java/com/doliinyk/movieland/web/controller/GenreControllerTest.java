package com.doliinyk.movieland.web.controller;

import com.doliinyk.movieland.entity.Genre;
import com.doliinyk.movieland.service.GenreService;
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
public class GenreControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    GenreController genreController;

    @Mock
    GenreService genreServiceMock;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

    @Test
    public void GetAll() throws Exception {
        List<Genre> genres = new ArrayList<>();

        genres.add(new Genre(1, "драма"));
        genres.add(new Genre(2, "триллер"));

        when(genreServiceMock.getAll()).thenReturn(genres);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("драма")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("триллер")));
    }
}
