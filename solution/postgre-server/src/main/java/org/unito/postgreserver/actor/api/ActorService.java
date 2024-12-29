package org.unito.postgreserver.actor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.actor.model.Actor;

import java.util.List;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(final ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getActorByFilter(String name, String role) {
        Specification<Actor> specification = combineWithAnd(List.of(
            equalsTo("name", name),
            equalsTo("role", role)
        ));

        return actorRepository.findAll(specification);
    }

    public List<Actor> getAllActors(String name) {
        // TODO: select distinct name
        return null;
    }
}
