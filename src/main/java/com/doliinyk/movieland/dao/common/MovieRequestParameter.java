package com.doliinyk.movieland.dao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MovieRequestParameter {
    private ArrayList<MovieOrderParameter> movieOrderParameter = new ArrayList<>();
    private Currency currency;
    private static Set<String> validationSet = new HashSet<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    static {
        validationSet.add((MovieOrderColumn.PRICE.getMovieOrderColumn() + ":" + OrderType.ASC.getOrderTypeName()));
        validationSet.add((MovieOrderColumn.PRICE.getMovieOrderColumn() + ":" + OrderType.DESC.getOrderTypeName()));
        validationSet.add((MovieOrderColumn.RATING.getMovieOrderColumn() + ":" + OrderType.DESC.getOrderTypeName()));
    }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public void addMovieOrderParameter(String name, OrderType orderType) throws IllegalArgumentException {
        if (orderType == null) return;
        logger.debug("name: {}; orderType: {}", name, orderType.getOrderTypeName());
        logger.debug("validationSet: {}", validationSet);

        MovieOrderColumn moc = MovieOrderColumn.getMovieOrderColumnByValue(name);
        if (validationSet.contains(moc.getMovieOrderColumn() + ":" + orderType.getOrderTypeName())) {
            movieOrderParameter.add(new MovieOrderParameter(moc, orderType));
        } else {
            throw new IllegalArgumentException(
                    "Invalid combination of name: '" + name + "', order: '" + orderType.getOrderTypeName() + "'"
            );
        }
    }

    public int orderParameterSize() { return movieOrderParameter.size(); }

    public MovieOrderParameter getMovieOrderParameter(int i) {
        return movieOrderParameter.get(i);
    }

    @Override
    public String toString() {
        return "MovieRequestParameter{" +
                "movieOrderParameter=" + movieOrderParameter +
                ", currency=" + currency +
                '}';
    }
}
