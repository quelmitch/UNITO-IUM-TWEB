package org.unito.postgreserver.crew.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping("/filter")
    public Map<String, Object> getCrewMembers(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute CrewFilterDTO crewFilter
    ) {
        return crewService.getCrewMembersByFilter(genericFilter, crewFilter);
    }
}
