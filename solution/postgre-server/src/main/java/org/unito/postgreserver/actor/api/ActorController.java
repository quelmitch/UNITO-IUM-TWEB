package org.unito.postgreserver.actor.api;

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

    @GetMapping("")
    public Map<String, Object> getActorsByName(@ModelAttribute GenericFilterDTO genericFilter,
                                               @ModelAttribute ActorFilterDTO actorFilter
    ) {
        return actorService.getActorsByName(genericFilter, actorFilter);
    }

    @GetMapping("/filter")
    public Map<String, Object> getActors(@ModelAttribute GenericFilterDTO genericFilter,
                                         @ModelAttribute ActorFilterDTO actorFilter
    ) {
        return actorService.getActorByFilter(genericFilter, actorFilter);
    }
}
