package org.unito.postgreserver.movie.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.unito.postgreserver.movie.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    // TODO: Need to run this query before CREATE EXTENSION IF NOT EXISTS pg_trgm;
    // TODO: Make the query more strict now its unusable
    @Query(value = "SELECT * FROM movie WHERE (title ILIKE :searchTerm || '%' OR title % :searchTerm) OR to_tsvector('english', title) @@ to_tsquery('english', :searchTerm)", nativeQuery = true)
    List<Movie> searchMovies(@Param("searchTerm") String searchTerm);
}
