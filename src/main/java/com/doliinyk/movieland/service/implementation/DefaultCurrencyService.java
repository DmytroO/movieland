package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.entity.Currency;
import com.doliinyk.movieland.service.CurrencyService;

public class DefaultCurrencyService implements CurrencyService {

    @Override
    public double convertToCurrency(double sum, Currency toCurrency) {
        return sum;
    }
}
