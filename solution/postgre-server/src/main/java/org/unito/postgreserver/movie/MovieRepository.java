package org.unito.postgreserver.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    // TODO: Custom queries go here

    @Query(value = "SELECT * FROM movie WHERE (title ILIKE :searchTerm || '%' OR title % :searchTerm) OR to_tsvector('english', title) @@ to_tsquery('english', :searchTerm)", nativeQuery = true)
    List<Movie> searchMovies(@Param("searchTerm") String searchTerm);
}
