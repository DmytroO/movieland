package com.doliinyk.movieland.service;

import com.doliinyk.movieland.dao.GenreDao;
import com.doliinyk.movieland.entity.Genre;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultGenreServiceTest {
    @Test
    public void getAll() {
        GenreDao genreDao = mock(GenreDao.class);
        List<Genre> expectedGenres = new ArrayList<>();

        expectedGenres.add(new Genre(1 ,"драма"));
        expectedGenres.add(new Genre(2 ,"триллер"));

        when(genreDao.getAll()).thenReturn(expectedGenres);

        DefaultGenreService defaultGenreService = new DefaultGenreService(genreDao);
        List<Genre> actualGenres = defaultGenreService.getAll();

        assertEquals(2, actualGenres.size());
        for (int i = expectedGenres.size() - 1; i >= 0; i--) {
            assertTrue(actualGenres.remove(expectedGenres.get(i)));
        }
        assertEquals(0 ,actualGenres.size());
    }

}
