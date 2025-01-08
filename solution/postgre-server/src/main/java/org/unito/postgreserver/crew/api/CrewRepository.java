package org.unito.postgreserver.crew.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.crew.model.Crew;

public interface CrewRepository extends JpaRepository<Crew, Long>, JpaSpecificationExecutor<Crew> {
    @Query(value = "SELECT DISTINCT c.name FROM Crew c WHERE c.name LIKE :namePatten",
            countQuery = "SELECT count(*) FROM Crew c WHERE c.name LIKE :namePatten")
    Page<String> findDistinctCrewMembers(String namePatten, Pageable pageable);

    @Query(value = "SELECT DISTINCT c.name FROM Crew c",
            countQuery = "SELECT count(*) FROM Crew c")
    Page<String> findDistinctCrewMembers(Pageable pageable);
}
