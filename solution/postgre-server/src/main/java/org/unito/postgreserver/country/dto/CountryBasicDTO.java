package org.unito.postgreserver.country.dto;

import lombok.*;
import org.unito.postgreserver.country.model.Country;
import org.unito.postgreserver.country.model.CountryType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class CountryBasicDTO implements CountryType {
    private String country;

    public static CountryBasicDTO toDTO(Country country) {
        CountryBasicDTO dto = new CountryBasicDTO();
        country.setCountry(country.getCountry());
        return dto;
    }
}
