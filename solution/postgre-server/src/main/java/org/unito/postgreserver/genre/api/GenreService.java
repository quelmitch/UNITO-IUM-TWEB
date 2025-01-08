package org.unito.postgreserver.genre.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(final GenreRepository genreRepository) { this.genreRepository = genreRepository; }
}
