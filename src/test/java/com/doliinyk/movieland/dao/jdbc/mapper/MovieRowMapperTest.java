package com.doliinyk.movieland.dao.jdbc.mapper;

import com.doliinyk.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRowMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("nameRussian")).thenReturn("Побег из Шоушенка");
        when(resultSet.getString("nameNative")).thenReturn("The Shawshank Redemption");
        when(resultSet.getInt("yearOfRelease")).thenReturn(1994);
        when(resultSet.getDouble("rating")).thenReturn(8.9);
        when(resultSet.getDouble("price")).thenReturn(123.99);
        when(resultSet.getString("picturePath")).thenReturn("shawshank_picture.jpb");

        MovieRowMapper movieRowMapper = new MovieRowMapper();
        Movie movie = movieRowMapper.mapRow(resultSet ,0);

        assertNotNull(movie);
        assertEquals("Побег из Шоушенка", movie.getNameRussian());
        assertEquals("The Shawshank Redemption", movie.getNameNative());
        assertEquals(1994, movie.getYearOfRelease());
        assertEquals(8.9, movie.getRating(), 0d);
        assertEquals(123.99 ,movie.getPrice(), 0d);
        assertEquals("shawshank_picture.jpb", movie.getPicturePath());
    }
}
