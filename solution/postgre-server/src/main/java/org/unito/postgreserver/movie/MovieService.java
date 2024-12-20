package org.unito.postgreserver.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.utils.SpecificationUtility;

import java.util.List;
import java.util.stream.Collectors;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class MovieService {
    // TODO: Business Logic, calculations, data transformation go here

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public List<Movie> getMovieByTitle(String title) {
        return movieRepository.searchMovies(title);
    }

    public List<Movie> getMovieWithFilter(MovieFilterDTO filter) {
        Specification<Movie> spec = combineWithAnd(List.of(
                greaterThan("durationInMinutes", filter.getDurationGT()),
                lessThan("durationInMinutes", filter.getDurationLT()),
                greaterThan("releaseYear", filter.getReleaseYearGT()),
                lessThan("releaseYear", filter.getReleaseYearLT()),
                greaterThan("rating", filter.getRatingGT()),
                lessThan("rating", filter.getRatingLT())
        ));

        return movieRepository.findAll(spec)
            .stream()
            .skip(filter.getOffset())
            .limit(filter.getLimit())
            .collect(Collectors.toList());
    }
}