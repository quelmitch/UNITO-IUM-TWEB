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
                .where(SpecificationUtility.specEquals("title", title));
        return movieRepository.findAll(spec);
    }

    public List<Movie> getMovieWithFilter(MovieFilterDTO filter) {
        Specification<Movie> spec = SpecificationUtility.specCombine(List.of(
                SpecificationUtility.specGreaterThan("durationInMinutes", filter.getDurationGT()),
                SpecificationUtility.specLessThan("durationInMinutes", filter.getDurationLT()),
                SpecificationUtility.specGreaterThan("releaseYear", filter.getReleaseYearGT()),
                SpecificationUtility.specLessThan("releaseYear", filter.getReleaseYearLT()),
                SpecificationUtility.specGreaterThan("rating", filter.getRatingGT()),
                SpecificationUtility.specLessThan("rating", filter.getRatingLT())
        ));

        return movieRepository.findAll(spec)
            .stream()
            .skip(filter.getOffset())
            .limit(filter.getLimit())
            .collect(Collectors.toList());
    }
}