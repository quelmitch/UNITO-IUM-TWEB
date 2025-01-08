package org.unito.postgreserver.country.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.actor.model.Actor;
import org.unito.postgreserver.country.model.Country;
import org.unito.postgreserver.country.model.CountryType;

import java.util.List;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(final CountryRepository countryRepository) { this.countryRepository = countryRepository; }

    public List<? extends CountryType> getAllCountries() {
        Specification<Country> specification = combineWithAnd(List.of(
                like("country", null),
                distinct()
        ));

        return countryRepository.findAll(specification);
    }
}
