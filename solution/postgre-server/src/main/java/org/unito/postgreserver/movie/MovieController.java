package org.unito.postgreserver.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    // TODO: HTTP routes go here

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/filter")
    public List<Movie> getMovies(@ModelAttribute MovieDTO filters) {
        return movieService.getMovieWithFilter(filters);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/title")
    public List<Movie> getMovieByTitle(@RequestParam(required = false) String title) {
        return movieService.getMovieByTitle(title);
    }
}
