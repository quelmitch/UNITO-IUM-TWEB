package org.unito.postgreserver.release.api;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unito.postgreserver.release.dto.ReleaseFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/release")
public class ReleaseController {
    private final ReleaseService releaseService;

    @Autowired
    public ReleaseController(final ReleaseService releaseService) { this.releaseService = releaseService; }

    @GetMapping("/filter")
    public Map<String, Object> getReleases(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute ReleaseFilterDTO releaseFilter) {
        return releaseService.getReleaseByFilters(genericFilter, releaseFilter);
    }
}
