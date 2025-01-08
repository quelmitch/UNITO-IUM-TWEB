package org.unito.postgreserver.crew.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unito.postgreserver.actor.dto.ActorFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/crew")
public class CrewController {
    private final CrewService crewService;

    @Autowired
    public CrewController(final CrewService crewService) { this.crewService = crewService; }

    @GetMapping("")
    public Map<String, Object> getCrewByName(@ModelAttribute GenericFilterDTO genericFilter, @ModelAttribute ActorFilterDTO actorFilter) {
        return crewService.getCrewByName(genericFilter, actorFilter);
    }
}
