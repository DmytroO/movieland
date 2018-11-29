package com.doliinyk.movieland.dao.common;

import java.beans.PropertyEditorSupport;

public class OrderTypeEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        OrderType orderType = (OrderType) getValue();
        return orderType == null ? "" : orderType.getOrderTypeName();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        OrderType orderType = OrderType.getOrderTypeByValue(text);
        setValue(orderType);
    }
}
