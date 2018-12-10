package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.GenreDao;
import com.doliinyk.movieland.entity.Genre;
import com.doliinyk.movieland.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGenreService implements GenreService {
    private GenreDao genreDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public DefaultGenreService(GenreDao genreDao) { this.genreDao = genreDao; }

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = genreDao.getAll();
        logger.trace("genres: {}", genres);
        logger.info("genres.size: {}", genres.size());
        return genres;
    }

    @Override
    public List<Genre> getGenreByMovieId(int movieId) {
        return genreDao.getByMovieId(movieId);
    }
}
