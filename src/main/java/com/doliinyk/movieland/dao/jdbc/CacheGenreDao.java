package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.GenreDao;
import com.doliinyk.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class CacheGenreDao implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private GenreDao genreDao;
    private volatile List<Genre> genreCache;

    @PostConstruct
    @Scheduled (
            initialDelayString="${cacheGenre.refresh.initialDelay}",
            fixedDelayString="${cacheGenre.refresh.fixedDelay}"
    )
    private void refresh() {
        List<Genre> genres = genreDao.getAll();
        genreCache = genres;
        log.trace("refreshed");
    }

    @Override
    public List<Genre> getAll() {
        log.trace("getAll: genreCache.size {}" ,genreCache);
        return new ArrayList<>(genreCache);
    }

    public CacheGenreDao(GenreDao genreDao) { this.genreDao = genreDao; }

}
