package org.unito.postgreserver.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.movie.dto.MovieDto;
import org.unito.postgreserver.movie.model.*;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.function.Function;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovieByTitle(GenericFilterDTO genericFilters, String title) {
        Pageable pageable = PageRequest.of(
                genericFilters.getOffset() / genericFilters.getLimit(),
                genericFilters.getLimit()
        );

        return movieRepository.searchMovies(title, pageable).getContent();
    }

    public List<?> getMovieWithFilter(GenericFilterDTO genericFilters, MovieFilterDTO movieFilter) {
        Specification<Movie> specification = combineWithAnd(List.of(
            greaterThan("durationInMinutes", movieFilter.getDurationGT()),
            lessThan("durationInMinutes", movieFilter.getDurationLT()),
            greaterThan("releaseYear", movieFilter.getReleaseYearGT()),
            lessThan("releaseYear", movieFilter.getReleaseYearLT()),
            greaterThan("rating", movieFilter.getRatingGT()),
            lessThan("rating", movieFilter.getRatingLT()),
            like("movieId", "name", movieFilter.getActorName()) // TODO not tested yet
        ));

        Pageable pageable = PageRequest.of(
                genericFilters.getOffset() / genericFilters.getLimit(),
                genericFilters.getLimit(),
                Sort.by(Sort.Order.asc(movieFilter.getSort())) // TODO dynamic asc desc
        );

        return movieRepository.findAll(specification, pageable)
            .map(getMapper(genericFilters.getResponseType())) // Apply mapping to each Movie object
            .getContent();
    }

    private Function<Movie, ?> getMapper(GenericFilterDTO.Type mapTo) {
        return switch (mapTo) {
            case BASIC -> MovieDto::toDTO;
            case FULL  -> x->x;
        };
    }
}