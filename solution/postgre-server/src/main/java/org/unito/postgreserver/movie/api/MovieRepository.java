package org.unito.postgreserver.movie.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.unito.postgreserver.movie.model.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    @Query(value =
            """
            SELECT * FROM movie
            WHERE (title ILIKE :searchTerm || '%'  -- Prefix Search
                OR title = :searchTerm  -- Perfect Match
                OR to_tsvector('english', title) @@ to_tsquery('english', :searchTerm)  -- Full-text search
            )
            ORDER BY
                -- Perfect Match + runtime not null + rating not null
                CASE
                    WHEN title = :searchTerm AND rating IS NOT NULL AND duration_in_minutes IS NOT NULL THEN 1
                    ELSE 2
                END,
                -- Prefix Search
                CASE
                    WHEN title ILIKE :searchTerm || '%' THEN 1
                    ELSE 2
                END,
                -- Full-text search
                CASE
                    WHEN to_tsvector('english', title) @@ to_tsquery('english', :searchTerm) THEN 1
                    ELSE 2
                END
            """,
            countQuery = """
            SELECT count(*) FROM movie
            WHERE (title ILIKE :searchTerm || '%'  -- Prefix Search
                OR title = :searchTerm  -- Perfect Match
                OR to_tsvector('english', title) @@ to_tsquery('english', :searchTerm)  -- Full-text search
            )
            """,
            nativeQuery = true)
    Page<Movie> searchMovies(@Param("searchTerm") String searchTerm, Pageable pageable);
}
