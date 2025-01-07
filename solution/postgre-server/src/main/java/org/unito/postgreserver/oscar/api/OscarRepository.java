package org.unito.postgreserver.oscar.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.oscar.model.Oscar;

public interface OscarRepository extends JpaRepository<Oscar, Integer>, JpaSpecificationExecutor<Oscar> {
}
