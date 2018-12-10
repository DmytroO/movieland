package com.doliinyk.movieland.dao;

import com.doliinyk.movieland.entity.Genre;
import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
    List<Genre> getByMovieId(int movieId);
}
