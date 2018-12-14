package com.doliinyk.movieland.service.implementation;

import com.doliinyk.movieland.dao.CountryDao;
import com.doliinyk.movieland.entity.Country;
import com.doliinyk.movieland.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCountryService implements CountryService {
    private CountryDao countryDao;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public DefaultCountryService(CountryDao countryDao) { this.countryDao = countryDao; }

    @Override
    public List<Country> getCountryByMovie(int movieId) {
        List<Country> countries = countryDao.getCountryByMovie(movieId);
        log.trace("getCountryByMovie; countries: {}", countries);
        log.info("countries.size {}", countries.size());
        return countries;
    }
}
