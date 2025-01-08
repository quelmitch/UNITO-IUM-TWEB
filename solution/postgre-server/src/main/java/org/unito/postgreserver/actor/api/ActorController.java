package org.unito.postgreserver.actor.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.actor.dto.ActorFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

@RestController
@RequestMapping("/actor")
@Tag(name = "Actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(final ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/filter")
    public Map<String, Object> getActors(
            @ParameterObject @ModelAttribute GenericFilterDTO genericFilter,
            @ParameterObject @ModelAttribute ActorFilterDTO actorFilter
    ) {
        return actorService.getActorByFilter(genericFilter, actorFilter);
    }
}
