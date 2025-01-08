package org.unito.postgreserver.actor.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.actor.dto.ActorBasicDTO;
import org.unito.postgreserver.actor.dto.ActorFilterDTO;
import org.unito.postgreserver.actor.model.Actor;
import org.unito.postgreserver.actor.model.ActorType;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.buildResponse;
import static org.unito.postgreserver.utils.ServiceCommon.setPageable;
import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(final ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Map<String, Object> getActorsByName(GenericFilterDTO genericFilter, String name) {

        Pageable pageable = setPageable(genericFilter, "name");
        Page<String> actorPage;
        if (name != null)
            actorPage = actorRepository.findDistinctActors("%"+name+"%", pageable);
        else
            actorPage = actorRepository.findDistinctActors(pageable);

        return buildResponse(genericFilter, actorPage.getTotalPages(), actorPage.getContent());
    }

    public Map<String, Object> getActorByFilter(GenericFilterDTO genericFilter, ActorFilterDTO actorFilter) {
        Specification<Actor> specification = combineWithAnd(List.of(
            like("name", actorFilter.getName()),
            equalsTo("role", actorFilter.getRole())
        ));

        Pageable pageable = setPageable(genericFilter, actorFilter.getSortBy());
        Page<Actor> actorPage = actorRepository.findAll(specification, pageable);
        List<? extends ActorType> actors = mapActors(actorPage.getContent(), genericFilter.getResponseType());

        return buildResponse(genericFilter, actorPage.getTotalPages(), actors);
    }

    private List<? extends ActorType> mapActors(List<Actor> actors, GenericFilterDTO.Type responseType) {
        return switch (responseType) {
            case FULL -> actors;
            case BASIC -> actors.stream()
                .map(ActorBasicDTO::toDTO)
                .toList();
        };
    }
}
