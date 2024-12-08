package org.unito.postgreserver.movie;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
    // TODO: Business Logic, calculations, data transformation go here

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
