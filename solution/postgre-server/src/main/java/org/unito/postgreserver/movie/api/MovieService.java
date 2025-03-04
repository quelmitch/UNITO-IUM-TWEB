package org.unito.postgreserver.movie.api;

import org.postgresql.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.unito.postgreserver.movie.dto.MovieBasicDTO;
import org.unito.postgreserver.movie.model.*;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.buildResponse;
import static org.unito.postgreserver.utils.ServiceCommon.setPageable;
import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
    }

    public Map<String, Object> getMovieWithFilter(GenericFilterDTO genericFilter, MovieFilterDTO movieFilter) {
        Specification<Movie> specification = combineWithAnd(List.of(
            like("title", movieFilter.getTitle()),
            greaterThan("runtime", movieFilter.getRuntimeGT()),
            lessThan("runtime", movieFilter.getRuntimeLT()),
            greaterThan("releaseYear", movieFilter.getReleaseYearGT()),
            lessThan("releaseYear", movieFilter.getReleaseYearLT()),
            greaterThan("rating", movieFilter.getRatingGT()),
            lessThan("rating", movieFilter.getRatingLT()),
            like("actors", "name", movieFilter.getActor()),
            like("actors", "role", movieFilter.getCharacter()),
            like("crew", "name", movieFilter.getCrew()),
            equalsTo("genres", "genre", movieFilter.getGenre()),
            equalsTo("countries", "country", movieFilter.getProductionCountry()),
            equalsTo("languages", "language", movieFilter.getLanguage()),
            equalsTo("studios", "studio", movieFilter.getStudio())
        ));

        Pageable pageable = setPageable(genericFilter, movieFilter.getSortBy());
        Page<Movie> moviePage = movieRepository.findAll(specification, pageable);
        List<? extends MovieType> movies = mapMovies(moviePage.getContent(), genericFilter.getResponseType());

        return buildResponse(genericFilter, moviePage.getTotalPages(), movies);
    }

    private List<? extends MovieType> mapMovies(List<Movie> movies, GenericFilterDTO.Type responseType) {
        return switch (responseType) {
            case FULL -> movies;
            case BASIC -> movies.stream()
                    .map(MovieBasicDTO::toDTO)
                    .toList();
        };
    }
}