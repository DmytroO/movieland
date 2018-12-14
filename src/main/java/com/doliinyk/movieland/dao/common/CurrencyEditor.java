package com.doliinyk.movieland.dao.common;

import java.beans.PropertyEditorSupport;

public class CurrencyEditor extends PropertyEditorSupport {
    @Override
    public String getAsText() {
        Currency currency = (Currency) getValue();
        return currency == null ? "" : currency.getCurrencyValue();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Currency currency = Currency.getCurrencyByValue(text);
        setValue(currency);
    }
}
