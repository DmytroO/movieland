package com.doliinyk.movieland.web.controller;

import com.doliinyk.movieland.entity.Genre;
import com.doliinyk.movieland.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) { this.genreService = genreService; }

    @RequestMapping(path = "/v1/genre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Genre> getAll() { return genreService.getAll(); }

}
