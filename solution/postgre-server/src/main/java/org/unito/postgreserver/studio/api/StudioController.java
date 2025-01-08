package org.unito.postgreserver.studio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unito.postgreserver.studio.dto.StudioFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/studio")
public class StudioController {
    private final StudioService studioService;

    @Autowired
    public StudioController(final StudioService studioService) { this.studioService = studioService; }

    @GetMapping("")
    public Map<String, Object> getStudiosByName(
            @ModelAttribute GenericFilterDTO genericFilter,
            @ModelAttribute StudioFilterDTO studioFilter
    ) {
        return studioService.getAllStudios(genericFilter, studioFilter);
    }
}
