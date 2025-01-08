package org.unito.postgreserver.movie.api;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.movie.model.Movie;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/title")
    public List<Movie> getMovieByTitle(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilters,
            @ParameterObject @RequestParam String title
    ) {
        return movieService.getMovieByTitle(genericFilters, title);
    }

    @GetMapping("/filter")
    public Map<String, Object> getMovies(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilters,
            @ParameterObject @ModelAttribute MovieFilterDTO movieFilters
    ) {
        return movieService.getMovieWithFilter(genericFilters, movieFilters);
    }
}
