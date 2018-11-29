package com.doliinyk.movieland.dao.common;


import java.util.HashMap;
import java.util.Map;

public enum MovieOrderColumn  {
    PRICE("price"),
    RATING("rating");

    private final String movieOrderColumn;
    static Map<String, MovieOrderColumn> columnMap = new HashMap<>();

    static {
        for(MovieOrderColumn moc : MovieOrderColumn.values())
            columnMap.put(moc.getMovieOrderColumn(), moc);
    }

    public String getMovieOrderColumn() { return movieOrderColumn; }

    MovieOrderColumn(String movieOrderColumn) { this.movieOrderColumn = movieOrderColumn; }

    public static MovieOrderColumn getMovieOrderColumnByValue(String column) throws NoSuchFieldError {
        MovieOrderColumn movieOrderColumn = columnMap.get(column.trim().toLowerCase());
        if (movieOrderColumn == null)
            throw new NoSuchFieldError("no such value: " + column.trim().toLowerCase());
        return movieOrderColumn;
    }

    public static void main(String[] args) {
        MovieOrderColumn movieOrderColumn = MovieOrderColumn.PRICE;
        System.out.println(movieOrderColumn.getMovieOrderColumn());
    }
}
