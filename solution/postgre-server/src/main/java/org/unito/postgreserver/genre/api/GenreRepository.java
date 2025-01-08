package org.unito.postgreserver.genre.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.genre.model.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {
    @Query("SELECT DISTINCT g.genre FROM Genre g")
    List<String> findDistinctGenre();
}
