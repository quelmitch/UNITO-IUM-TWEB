package org.unito.postgreserver.crew.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unito.postgreserver.crew.dto.CrewFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/crew")
public class CrewController {
    private final CrewService crewService;

    @Autowired
    public CrewController(final CrewService crewService) { this.crewService = crewService; }

    @GetMapping("/name")
    public Map<String, Object> GetCrewMembersByName(@ModelAttribute GenericFilterDTO genericFilter,
                                               @ModelAttribute CrewFilterDTO crewFilter
    ) {
        return crewService.getCrewMembersByName(genericFilter, crewFilter);
    }

    @GetMapping("/filter")
    public Map<String, Object> GetCrewMembers(@ModelAttribute GenericFilterDTO genericFilter,
                                         @ModelAttribute CrewFilterDTO crewFilter
    ) {
        return crewService.getCrewMembersByFilter(genericFilter, crewFilter);
    }
}
