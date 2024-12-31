package org.unito.postgreserver.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.movie.dto.MovieDto;
import org.unito.postgreserver.movie.model.*;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.unito.postgreserver.utils.GenericFilterDTO.Sort.ASC;
import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public List<Movie> getMovieByTitle(GenericFilterDTO genericFilters, String title) {
        Pageable pageable = PageRequest.of(
            genericFilters.getPage(),
            genericFilters.getLimit()
        );

        return movieRepository.searchMovies(title, pageable).getContent();
    }

    public Map<String, Object> getMovieWithFilter(GenericFilterDTO genericFilters, MovieFilterDTO movieFilter) {
        Specification<Movie> specification = combineWithAnd(List.of(
            greaterThan("durationInMinutes", movieFilter.getDurationGT()),
            lessThan("durationInMinutes", movieFilter.getDurationLT()),
            greaterThan("releaseYear", movieFilter.getReleaseYearGT()),
            lessThan("releaseYear", movieFilter.getReleaseYearLT()),
            greaterThan("rating", movieFilter.getRatingGT()),
            lessThan("rating", movieFilter.getRatingLT()),
            like("movieId", "name", movieFilter.getActorName()) // TODO not tested yet
        ));

        Sort.Order sorting = genericFilters.getSort() == ASC ? Sort.Order.asc(movieFilter.getSortBy()) : Sort.Order.desc(movieFilter.getSortBy());

        Pageable pageable = PageRequest.of(
            genericFilters.getPage(),
            genericFilters.getLimit(),
            Sort.by(sorting)
        );

        Page<Movie> moviePage = movieRepository.findAll(specification, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("movies", moviePage.getContent().stream()
            .map(getMapper(genericFilters.getResponseType()))
            .collect(Collectors.toList()));
        response.put("totalPages", moviePage.getTotalPages());
        response.put("limit", genericFilters.getLimit());
        response.put("page", genericFilters.getPage());

        return response;
    }

    private Function<Movie, ?> getMapper(GenericFilterDTO.Type mapTo) {
        return switch (mapTo) {
            case BASIC -> MovieDto::toDTO;
            case FULL  -> x->x;
        };
    }
}