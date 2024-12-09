package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unito.postgreserver.movie.MovieService;

@RestController
@RequestMapping("/oscar")
public class OscarController {
    // TODO: HTTP routes go here

    private final OscarService oscarService;

    @Autowired
    public OscarController(OscarService oscarService) {
        this.oscarService = oscarService;
    }
}
