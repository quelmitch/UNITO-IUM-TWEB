package org.unito.postgreserver.release.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/release")
public class ReleaseController {
    private final ReleaseService releaseService;

    @Autowired
    public ReleaseController(final ReleaseService releaseService) { this.releaseService = releaseService; }
}
