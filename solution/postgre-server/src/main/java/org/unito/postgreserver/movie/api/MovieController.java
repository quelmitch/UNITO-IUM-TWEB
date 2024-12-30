package org.unito.postgreserver.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.movie.model.Movie;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;

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
    public List<Movie> getMovieByTitle(@ModelAttribute GenericFilterDTO genericFilters, @RequestParam String title) {
        return movieService.getMovieByTitle(genericFilters, title);
    }

    @GetMapping("/filter")
    public List<?> getMovies(@ModelAttribute GenericFilterDTO genericFilters, @ModelAttribute MovieFilterDTO movieFilters) {
        return movieService.getMovieWithFilter(genericFilters, movieFilters);
    }
}
