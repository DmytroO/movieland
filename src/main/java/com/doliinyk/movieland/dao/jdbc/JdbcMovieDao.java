package com.doliinyk.movieland.dao.jdbc;

import com.doliinyk.movieland.dao.MovieDao;
import com.doliinyk.movieland.dao.common.MovieQueryBuilder;
import com.doliinyk.movieland.dao.common.MovieRequestParameter;
import com.doliinyk.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.doliinyk.movieland.entity.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_MOVIE = "select m.id ,m.name_russian as nameRussian ,m.name_native as nameNative" +
            " ,m.year_of_release as yearOfRelease ,m.rating ,m.price ,p.poster_url as picturePath" +
            " from       movieland_movie  m " +
            " inner join movieland_poster p on p.movie_id = m.id"
            ;
    private static final String GET_3_RANDOM_MOVIES = "select id ,nameRussian ,nameNative ,yearOfRelease ,rating ,price ,picturePath\n" +
            "from      (\n" +
            "select m.id ,m.name_russian as nameRussian ,m.name_native as nameNative\n" +
            ",m.year_of_release as yearOfRelease ,m.rating ,m.price ,p.poster_url as picturePath\n" +
            "from       movieland_movie  m\n" +
            "inner join movieland_poster p on p.movie_id = m.id\n" +
            "limit 10\n" +
            "          ) a\n" +
            "order by random()\n" +
            "limit 3";
    private static final String GET_MOVIE_4_GENRE = "select m.id ,m.name_russian as nameRussian ,m.name_native as nameNative" +
            " ,m.year_of_release as yearOfRelease ,m.rating ,m.price ,p.poster_url as picturePath" +
            " from       movieland_movie       m " +
            " inner join movieland_poster      p  on p.movie_id = m.id" +
            " inner join movieland_movie_genre mg on mg.movie_id = m.id" +
            " where  mg.genre_id = ?"
            ;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAll(MovieRequestParameter movieRequestParameter) {
        String fullMovieSql = MovieQueryBuilder.movieQueryBuilder(GET_ALL_MOVIE, movieRequestParameter);
        return jdbcTemplate.query(fullMovieSql, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom() { return jdbcTemplate.query(GET_3_RANDOM_MOVIES, MOVIE_ROW_MAPPER); }

    @Override
    public List<Movie> getByGenre(int id, MovieRequestParameter movieRequestParameter) {
        String fullMovieSql = MovieQueryBuilder.movieQueryBuilder(GET_MOVIE_4_GENRE, movieRequestParameter);
        return jdbcTemplate.query(fullMovieSql, MOVIE_ROW_MAPPER ,id);
    }
}
