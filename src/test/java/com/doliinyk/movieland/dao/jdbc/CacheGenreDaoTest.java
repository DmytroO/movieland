package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.GenreDao;
import com.doliinyk.movieland.entity.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:testContext.xml")
public class CacheGenreDaoTest {
    @Autowired
    @SuppressWarnings("unused")
    private GenreDao cacheGenreDao;

    @Test
    public void getAll() {
        List<Genre> genres = cacheGenreDao.getAll();
        assertEquals(3, genres.size());
    }
}
