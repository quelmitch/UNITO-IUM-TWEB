package org.unito.postgreserver.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.utils.SpecificationUtility;

import java.util.List;
import java.util.stream.Collectors;

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
        Specification<Movie> spec = Specification
                .where(SpecificationUtility.equalsTo("title", title));
        return movieRepository.findAll(spec);
    }

    public List<Movie> getMovieWithFilter(MovieFilterDTO filter) {
        Specification<Movie> spec = SpecificationUtility.combineWithAnd(List.of(
                SpecificationUtility.greaterThan("durationInMinutes", filter.getDurationGT()),
                SpecificationUtility.lessThan("durationInMinutes", filter.getDurationLT()),
                SpecificationUtility.greaterThan("releaseYear", filter.getReleaseYearGT()),
                SpecificationUtility.lessThan("releaseYear", filter.getReleaseYearLT()),
                SpecificationUtility.greaterThan("rating", filter.getRatingGT()),
                SpecificationUtility.lessThan("rating", filter.getRatingLT())
        ));

        return movieRepository.findAll(spec)
            .stream()
            .skip(filter.getOffset())
            .limit(filter.getLimit())
            .collect(Collectors.toList());
    }
}