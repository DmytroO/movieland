package com.doliinyk.movieland.entity;

public class CurrencyRate {
    private Currency currency;
    private double rate;

    public CurrencyRate(Currency currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }
    public Currency getCurrency() { return currency; }
    public double getRate() { return rate; }
}
