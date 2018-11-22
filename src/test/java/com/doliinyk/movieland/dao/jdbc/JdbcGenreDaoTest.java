package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.jdbc.JdbcGenreDao;
import com.doliinyk.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.doliinyk.movieland.entity.Genre;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcGenreDaoTest {
    @Test
    public void getAll() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        List<Genre> expectedGenres = new ArrayList<>();

        expectedGenres.add(new Genre(1, "драма"));
        expectedGenres.add(new Genre(2, "триллер"));

        when(jdbcTemplate.query(any(String.class), any(GenreRowMapper.class))).thenReturn(expectedGenres);

        JdbcGenreDao jdbcGenreDao = new JdbcGenreDao(jdbcTemplate);
        List<Genre> actualGenres = jdbcGenreDao.getAll();

        assertEquals(2, actualGenres.size());
        for (int i = expectedGenres.size() - 1; i >= 0; i--) {
            assertTrue(actualGenres.remove(expectedGenres.get(i)));
        }
        assertEquals(0, actualGenres.size());
    }
}
