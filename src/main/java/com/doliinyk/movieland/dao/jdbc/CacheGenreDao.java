package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.GenreDao;
import com.doliinyk.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
@SuppressWarnings("unused")
public class CacheGenreDao implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private GenreDao genreDao;
    private volatile List<Genre> genreCache;

    @PostConstruct
    @Scheduled (
            initialDelayString="${cache.genre.refresh.initialDelay}",
            fixedDelayString="${cache.genre.refresh.fixedDelay}"
    )
    @SuppressWarnings("unused")
    private void refresh() {
        List<Genre> genres = genreDao.getAll();
        genreCache = genres;
        log.info("refreshed; genres.size: ", genres.size());
    }

    @Override
    public List<Genre> getAll() {
        return new ArrayList<>(genreCache);
    }

    public CacheGenreDao(GenreDao genreDao) { this.genreDao = genreDao; }

}
