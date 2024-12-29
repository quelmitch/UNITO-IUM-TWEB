package org.unito.postgreserver.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.unito.postgreserver.movie.dto.MovieDto;
import org.unito.postgreserver.movie.model.*;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovieByTitle(String title) {
        return movieRepository.searchMovies(title);
    }

    public List<?> getMovieWithFilter(MovieFilterDTO filter) {
        Specification<Movie> specification = combineWithAnd(List.of(
            greaterThan("durationInMinutes", filter.getDurationGT()),
            lessThan("durationInMinutes", filter.getDurationLT()),
            greaterThan("releaseYear", filter.getReleaseYearGT()),
            lessThan("releaseYear", filter.getReleaseYearLT()),
            greaterThan("rating", filter.getRatingGT()),
            lessThan("rating", filter.getRatingLT()),
            like("movieId", "name", filter.getActorName())
        ));

        Pageable pageable = PageRequest.of(
                filter.getOffset() / filter.getLimit(),
                filter.getLimit()
        );

        return movieRepository.findAll(specification, pageable)
            .map(getMapper(filter.getResponseType())) // Apply mapping to each Movie object
            .getContent();
    }

    // TODO: Change mapTO type
    private Function<Movie, ?> getMapper(String mapTo) {
        return switch (mapTo) {
            case "DTO" -> MovieDto::toDTO;
            default -> x->x;
        };
    }
}