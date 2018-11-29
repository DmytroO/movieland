package com.doliinyk.movieland.dao.common;

public class MovieOrderParameter {
    private MovieOrderColumn movieOrderColumn;
    private OrderType orderType;

    public MovieOrderParameter(MovieOrderColumn movieOrderColumn, OrderType orderType) {
        this.movieOrderColumn = movieOrderColumn;
        this.orderType = orderType;
    }

    public MovieOrderColumn getMovieOrderColumn() {
        return movieOrderColumn;
    }

    public OrderType getOrderType() {
        return orderType;
    }
}
