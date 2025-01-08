package org.unito.postgreserver.actor.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.actor.dto.ActorBasicDTO;
import org.unito.postgreserver.actor.model.Actor;
import org.unito.postgreserver.country.dto.CountryBasicDTO;
import org.unito.postgreserver.movie.model.Movie;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long>, JpaSpecificationExecutor<Actor> {
    @Query(value = "SELECT DISTINCT a.name FROM Actor a WHERE a.name LIKE :namePatten",
           countQuery = "SELECT count(*) FROM Actor a WHERE a.name LIKE :namePatten")
    Page<String> findDistinctActors(String namePatten, Pageable pageable);

    @Query(value = "SELECT DISTINCT a.name FROM Actor a",
            countQuery = "SELECT count(*) FROM Actor a")
    Page<String> findDistinctActors(Pageable pageable);
}
