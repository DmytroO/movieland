package com.doliinyk.movieland.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml",
        "classpath:testContext.xml"
})
@WebAppConfiguration
public class MovieControllerITest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext  webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nameRussian", is("Фильм1")))
                .andExpect(jsonPath("$[0].nameNative", is("Movie1")))
                .andExpect(jsonPath("$[0].yearOfRelease", is(1998)))
                .andExpect(jsonPath("$[0].picturePath", is("picture1.jpg")))
                .andExpect(jsonPath("$[0].rating", is(8.9)))
                .andExpect(jsonPath("$[0].price", is(123.45)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nameRussian", is("Фильм2")))
                .andExpect(jsonPath("$[1].nameNative", is("Movie2")))
                .andExpect(jsonPath("$[1].yearOfRelease", is(1999)))
                .andExpect(jsonPath("$[1].picturePath", is("picture2.jpeg")))
                .andExpect(jsonPath("$[1].rating", is(9.0)))
                .andExpect(jsonPath("$[1].price", is(999.99)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].nameRussian", is("Фильм3")))
                .andExpect(jsonPath("$[2].nameNative", is("Movie3")))
                .andExpect(jsonPath("$[2].yearOfRelease", is(2000)))
                .andExpect(jsonPath("$[2].picturePath", is("picture3.png")))
                .andExpect(jsonPath("$[2].rating", is(9.9)))
                .andExpect(jsonPath("$[2].price", is(200.00)));
    }
    @Test
    public void getThreeRandom() throws Exception {
        mockMvc.perform(get("/v1/movie/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
