package org.unito.postgreserver.actor.api;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.actor.dto.ActorFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(final ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/name")
    public Map<String, Object> getActorsByName(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @RequestParam(required = false) String name
    ) {
        return actorService.getActorsByName(genericFilter, name);
    }

    @GetMapping("/filter")
    public Map<String, Object> getActors(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute ActorFilterDTO actorFilter
    ) {
        return actorService.getActorByFilter(genericFilter, actorFilter);
    }
}
