package com.doliinyk.movieland.dao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public enum OrderType {
    ASC("asc"),
    DESC("desc");

    private final String orderType;
    private static Map<String, OrderType> fieldOrderMap = new HashMap<>();

    static {
        for(OrderType orderType : OrderType.values()) {
            fieldOrderMap.put(orderType.getOrderTypeName(), orderType);
        }
    }

    public static OrderType getOrderTypeByValue(String orderString) throws IllegalArgumentException {
        OrderType orderType = fieldOrderMap.get(orderString.trim().toLowerCase());
        if (orderType == null)
            throw new NoSuchFieldError("orderType: invalid value " + orderString.trim());
        return orderType;
    }

    public String getOrderTypeName() { return orderType; }

    OrderType(String orderType) {
        this.orderType = orderType;
    }
}
