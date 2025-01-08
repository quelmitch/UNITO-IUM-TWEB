package org.unito.postgreserver.country.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.country.model.Country;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
    @Query("SELECT DISTINCT c.country FROM Country c")
    List<String> findDistinctCountry();
}
