package org.unito.postgreserver.crew.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.actor.dto.ActorBasicDTO;
import org.unito.postgreserver.actor.dto.ActorFilterDTO;
import org.unito.postgreserver.actor.model.Actor;
import org.unito.postgreserver.actor.model.ActorType;
import org.unito.postgreserver.crew.dto.CrewFilterDTO;
import org.unito.postgreserver.crew.model.Crew;
import org.unito.postgreserver.crew.model.CrewType;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.buildResponse;
import static org.unito.postgreserver.utils.ServiceCommon.setPageable;
import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class CrewService {
    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(final CrewRepository crewRepository) { this.crewRepository = crewRepository; }

    public Map<String, Object> getCrewByName(GenericFilterDTO genericFilter, CrewFilterDTO crewFilter) {
        Specification<Crew> specification = combineWithAnd(List.of(
                like("name", crewFilter.getName()),
                distinct()
        ));

        Pageable pageable = setPageable(genericFilter, crewFilter.getSortBy());
        Page<Crew> crewPage = crewRepository.findAll(specification, pageable);
        List<? extends CrewType> crews = mapActors(crewPage.getContent(), GenericFilterDTO.Type.BASIC);

        return buildResponse(genericFilter, crewPage.getTotalPages(), crews);
    }

    // TODO:
}
