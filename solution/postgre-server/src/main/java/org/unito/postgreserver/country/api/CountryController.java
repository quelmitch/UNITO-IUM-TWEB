package org.unito.postgreserver.country.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@Tag(name = "Countries")
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(final CountryService countryService) { this.countryService = countryService; }

    @GetMapping("")
    public List<String> getAllCountries() {
        return countryService.getAllCountries();
    }
}
