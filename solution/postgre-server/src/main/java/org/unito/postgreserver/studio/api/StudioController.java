package org.unito.postgreserver.studio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studio")
public class StudioController {
    private final StudioService studioService;

    @Autowired
    public StudioController(final StudioService studioService) { this.studioService = studioService; }
}
