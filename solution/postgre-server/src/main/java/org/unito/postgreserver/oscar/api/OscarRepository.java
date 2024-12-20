package org.unito.postgreserver.oscar.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.oscar.model.OscarNomination;

public interface OscarRepository extends JpaRepository<OscarNomination, Integer>, JpaSpecificationExecutor<OscarNomination> {
}
