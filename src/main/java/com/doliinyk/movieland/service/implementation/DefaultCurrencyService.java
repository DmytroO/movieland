package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.common.Currency;
import com.doliinyk.movieland.service.CurrencyService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class DefaultCurrencyService implements CurrencyService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final Logger log = LoggerFactory.getLogger(getClass());
    private volatile Map<Currency, Double> rates = new HashMap<>();

    private Currency nativeCurrency;
    private String currencyRateUrlString;

    @Autowired
    public DefaultCurrencyService(
            @Value("${currency.nativeCurrency}") String nativeCurrencyString,
            @Value("${currency.url}") String currencyRateUrlString
    ) {
        log.info("nativeCurrency {}, currencyRateUrlString {}");
        this.nativeCurrency = Currency.getCurrencyByValue(nativeCurrencyString);
        this.currencyRateUrlString = currencyRateUrlString;
    }

    @Override
    public double convertToCurrency(double sum, Currency toCurrency) {
        log.info("convertToCurrency");
        log.info("currencyRateUrlString {}; nativeCurrency {}, sum {}, toCurrency {}", currencyRateUrlString, nativeCurrency,
                sum, toCurrency);
        if (toCurrency == null)
            return sum;
        Double rate = rates.get(toCurrency);
        if (rate == null)
            return 0;
        return sum / rate;
    }

    @Scheduled(cron = "${currency.cron}")
    @PostConstruct
    public void refreshCurrencyRateCache() {
        String json = getCurrencyRateJson();
        try {
            parseJsonToRatesCache(json);
        } catch (Exception e) {
            log.error("RefreshRates \n{}", e);
        }
    }

    private String getCurrencyRateJson() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(currencyRateUrlString, String.class);
        return response.getBody();
    }

    private void parseJsonToRatesCache(String json) throws IOException {
        Map<Currency, Double> rates = new HashMap<>();
        JsonNode parent = mapper.readTree(json);
        Iterator<JsonNode> iterator = parent.elements();
        while (iterator.hasNext()) {
            JsonNode node = iterator.next();
            String currencyString = node.get("cc").asText();
            if (Currency.isCurrencyValueValid(currencyString))
                rates.put(Currency.getCurrencyByValue(currencyString), node.get("rate").asDouble());
        }
        rates.put(nativeCurrency, 1D);
        this.rates = rates;
    }
}
