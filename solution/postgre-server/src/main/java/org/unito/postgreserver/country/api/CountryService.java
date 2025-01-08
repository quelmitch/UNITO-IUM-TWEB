package org.unito.postgreserver.country.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.country.model.CountryType;

import java.util.List;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(final CountryRepository countryRepository) { this.countryRepository = countryRepository; }

    public List<String> getAllCountries() {
        return countryRepository.findDistinctCountry();
    }
}
