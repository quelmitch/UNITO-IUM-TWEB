package org.unito.postgreserver.studio.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.studio.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long>, JpaSpecificationExecutor<Studio> {
    @Query(value = "SELECT DISTINCT s.studio FROM Studio s WHERE s.studio LIKE :namePatten",
            countQuery = "SELECT count(*) FROM Studio s WHERE s.studio LIKE :namePatten")
    Page<String> findDistinctStudio(String namePatten, Pageable pageable);

    @Query(value = "SELECT DISTINCT s.studio FROM Studio s",
            countQuery = "SELECT count(*) FROM Studio s")
    Page<String> findDistinctStudio(Pageable pageable);
}
