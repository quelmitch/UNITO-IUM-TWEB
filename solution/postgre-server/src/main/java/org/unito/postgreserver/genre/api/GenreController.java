package org.unito.postgreserver.genre.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(final GenreService genreService) { this.genreService = genreService; }

    @GetMapping("")
    public List<String> getAllGenres() {
        return genreService.getAllGenres();
    }
}
