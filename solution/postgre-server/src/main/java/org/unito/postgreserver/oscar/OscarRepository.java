package org.unito.postgreserver.oscar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

interface OscarRepository extends JpaRepository<OscarCeremony, Integer> {
    @Query(value = "SELECT * FROM oscar_ceremony c JOIN oscar_nomination n ON c.number = n.ceremony_number WHERE c.year = :year", nativeQuery = true)
    List<OscarCeremony> findCeremoniesWithNominationsByYear(@RequestParam("year") int year);

    @Query(value = "SELECT * FROM oscar_ceremony c JOIN oscar_nomination n ON c.number = n.ceremony_number WHERE n.movie = :movie", nativeQuery = true)
    List<OscarCeremony> findCeremoniesByMovie(@RequestParam("movie") String movie);

    @Query(value = "SELECT * FROM oscar_ceremony c JOIN oscar_nomination n ON c.number = n.ceremony_number WHERE n.person = :person", nativeQuery = true)
    List<OscarCeremony> findCeremoniesByPerson(@RequestParam("person") String person);

    @Query(value = "SELECT * FROM oscar_ceremony c JOIN oscar_nomination n ON c.number = n.ceremony_number WHERE n.winner = true AND (:year IS NULL OR c.year = :year) AND (:number IS NULL OR c.number = :number)", nativeQuery = true)
    List<OscarCeremony> findWinningCeremonies(@RequestParam(value = "year", required = false) Integer year, @RequestParam(value = "number", required = false) Integer number);

    @Query(value = "SELECT * FROM oscar_ceremony c JOIN oscar_nomination n ON c.number = n.ceremony_number WHERE n.winner = true AND n.category = :category", nativeQuery = true)
    List<OscarCeremony> findWinnersByCategory(@RequestParam("category") String category);

    @Query(value = "SELECT * FROM oscar_ceremony c JOIN oscar_nomination n ON c.number = n.ceremony_number WHERE n.category = :category", nativeQuery = true)
    List<OscarCeremony> findCeremoniesByCategory(@RequestParam("category") String category);
}
