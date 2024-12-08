package org.unito.postgreserver.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // TODO: Custom queries go here
}
