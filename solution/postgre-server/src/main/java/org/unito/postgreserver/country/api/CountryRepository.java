package org.unito.postgreserver.country.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.country.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
}
