package org.unito.postgreserver.movie.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.movie.model.Movie;
import org.unito.postgreserver.movie.dto.MovieFilterDTO;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/filter")
    public List<Movie> getMovies(@ModelAttribute MovieFilterDTO filters) {
        return movieService.getMovieWithFilter(filters);
    }

    @GetMapping("/title")
    public List<Movie> getMovieByTitle(@RequestParam(required = false) String title) {
        return movieService.getMovieByTitle(title);
    }
}
