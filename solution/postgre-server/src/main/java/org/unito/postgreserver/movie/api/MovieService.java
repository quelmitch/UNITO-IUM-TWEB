package org.unito.postgreserver.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.movie.model.Movie;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;

import java.util.List;
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

    // TODO: valutare se between è utilizzabile in questo contesto (null??, un solo valore??)
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

    // TODO: con tante informazioni legate al film si potrebbero fare diversi metodi per prelevare
    //  un quantitativo diverso di informazioni oppure creare un nuovo DTO, o integrare quello già presente, per
    //  segnalare quali informazioni prelevare (es genere (true/false))
}