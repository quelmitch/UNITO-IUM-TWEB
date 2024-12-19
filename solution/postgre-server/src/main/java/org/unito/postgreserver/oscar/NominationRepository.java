package org.unito.postgreserver.oscar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.oscar.model.OscarCeremony;
import org.unito.postgreserver.oscar.model.OscarNomination;

// TODO rename into OscarRepository after completing refactoring
public interface NominationRepository  extends JpaRepository<OscarNomination, Integer>, JpaSpecificationExecutor<OscarNomination> {
}
