package com.doliinyk.movieland.service;

import com.doliinyk.movieland.dao.common.Currency;

public interface CurrencyService {
    double convertToCurrency(double sum, Currency toCurrency);

}
