package org.unito.postgreserver.crew.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.crew.dto.CrewFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/crew")
@Tag(name = "Crew")
public class CrewController {
    private final CrewService crewService;

    @Autowired
    public CrewController(final CrewService crewService) { this.crewService = crewService; }

    @GetMapping("/name")
    public Map<String, Object> getCrewMembersByName(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @RequestParam(required = false) String name
    ) {
        return crewService.getCrewMembersByName(genericFilter, name);
    }

    @GetMapping("/filter")
    public Map<String, Object> getCrewMembers(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute CrewFilterDTO crewFilter
    ) {
        return crewService.getCrewMembersByFilter(genericFilter, crewFilter);
    }
}
