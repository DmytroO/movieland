package com.doliinyk.movieland.dao.common;

public class MovieQueryBuilder {
    public static String movieQueryBuilder(String query, MovieRequestParameter requestParameter) {
        StringBuilder sql = new StringBuilder(query);
        String orderByClause = orderByBuilder(requestParameter);
        sql.append(orderByClause);
        return sql.toString();
    }

    public static String orderByBuilder(MovieRequestParameter requestParameter) {
        int orderColumnNumber = requestParameter.orderParameterSize();
        if (orderColumnNumber == 0) return " ";
        StringBuilder orderBy = new StringBuilder(" order by ");
        for(int i = 0; i < orderColumnNumber; i++) {
            if (i > 0) orderBy.append(",");
            MovieOrderParameter mop = requestParameter.getMovieOrderParameter(i);
            orderBy.append(mop.getMovieOrderColumn().getMovieOrderColumn());
            orderBy.append(" ");
            orderBy.append(mop.getOrderType().getOrderTypeName());
        }
        return orderBy.toString();
    }
}
