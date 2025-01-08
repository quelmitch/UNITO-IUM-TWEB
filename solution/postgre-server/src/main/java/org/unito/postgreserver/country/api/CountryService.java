package org.unito.postgreserver.country.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(final CountryRepository countryRepository) { this.countryRepository = countryRepository; }

    public List<String> getAllCountries() {
        return countryRepository.findDistinctCountry();
    }
}
