package org.unito.postgreserver.actor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unito.postgreserver.actor.model.Actor;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(final ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/filter")
    public List<Actor> getActors(@RequestParam String name, @RequestParam String role) {
        return actorService.getActorByFilter(name, role);
    }

    public List<Actor> getAllActors(@RequestParam String name) {
        // return actorService.
        return null;
    }
}
