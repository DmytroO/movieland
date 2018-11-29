package com.doliinyk.movieland.dao.common;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MovieOrderColumn  {
    PRICE("price"),
    RATING("rating");
    private static final Map<String, MovieOrderColumn> columnMap = Stream.of(MovieOrderColumn.values()).
            collect(Collectors.toMap(moc -> moc.getMovieOrderColumn(), moc -> moc));
    private final String movieOrderColumn;

    public String getMovieOrderColumn() { return movieOrderColumn; }

    MovieOrderColumn(String movieOrderColumn) { this.movieOrderColumn = movieOrderColumn; }

    public static MovieOrderColumn getMovieOrderColumnByValue(String column) throws NoSuchFieldError {
        MovieOrderColumn movieOrderColumn = columnMap.get(column.trim().toLowerCase());
        if (movieOrderColumn == null)
            throw new NoSuchFieldError("no such value: " + column.trim().toLowerCase());
        return movieOrderColumn;
    }
}
