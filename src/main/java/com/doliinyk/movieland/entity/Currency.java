package com.doliinyk.movieland.entity;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Currency {
    UAH("uah"),
    USD("usd"),
    EUR("eur");
    private final String currency;
    public static final Map<String, Currency> currencyMap = Stream.of(Currency.values())
            .collect(Collectors.toMap(currency -> currency.getCurrencyValue(), currency -> currency));

    Currency(String currency) { this.currency = currency; }

    public String getCurrencyValue() { return currency; }

    public static Currency getCurrencyByValue(String value) throws NoSuchFieldError {
        Currency currency = currencyMap.get(value.trim().toLowerCase());
        if (currency == null)
            throw new NoSuchFieldError("no such value: " + value.trim().toLowerCase());
        return currency;
    }
}
