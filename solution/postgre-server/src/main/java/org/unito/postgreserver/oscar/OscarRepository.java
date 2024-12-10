package org.unito.postgreserver.oscar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OscarRepository extends JpaRepository<OscarNomination, Integer>, JpaSpecificationExecutor<OscarNomination> {

    List<OscarNomination> findByWinnerTrue();

    List<OscarNomination> findByCeremony_Number(int ceremonyNumber);

    List<OscarNomination> findByMovie(String movie);

    List<OscarNomination> findByPerson(String person);

    List<OscarNomination> findByCategoryAndWinnerTrue(String category);

    List<OscarNomination> findByCategory(String category);
}

