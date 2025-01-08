package org.unito.postgreserver.crew.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.crew.model.Crew;

public interface CrewRepository extends JpaRepository<Crew, Long>, JpaSpecificationExecutor<Crew> {
}
