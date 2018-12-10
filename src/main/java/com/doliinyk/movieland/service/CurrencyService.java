package com.doliinyk.movieland.service;

import com.doliinyk.movieland.entity.Currency;

public interface CurrencyService {
    public double convertToCurrency(double sum, Currency toCurrency);
}
