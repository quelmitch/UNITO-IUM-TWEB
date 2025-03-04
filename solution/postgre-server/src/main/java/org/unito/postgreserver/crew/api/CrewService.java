package org.unito.postgreserver.crew.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.crew.dto.CrewBasicDTO;
import org.unito.postgreserver.crew.dto.CrewFilterDTO;
import org.unito.postgreserver.crew.model.Crew;
import org.unito.postgreserver.crew.model.CrewType;
import org.unito.postgreserver.utils.*;

import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.*;
import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class CrewService {
    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(final CrewRepository crewRepository) { this.crewRepository = crewRepository; }

    public Map<String, Object> getCrewMembersByFilter(GenericFilterDTO genericFilter, CrewFilterDTO crewFilter) {
        Specification<Crew> specification = combineWithAnd(List.of(
                like("name", crewFilter.getName()),
                equalsTo("role", crewFilter.getRole())
        ));

        Pageable pageable = setPageable(genericFilter, crewFilter.getSortBy());
        Page<Crew> crewPage = crewRepository.findAll(specification, pageable);
        List<? extends CrewType> crewMembers = mapCrewMembers(crewPage.getContent(), genericFilter.getResponseType());

        return buildResponse(genericFilter, crewPage.getTotalPages(), crewMembers);
    }

    private List<? extends CrewType> mapCrewMembers(List<Crew> crewMembers, GenericFilterDTO.Type responseType) {
        return switch (responseType) {
            case FULL -> crewMembers;
            case BASIC -> crewMembers.stream()
                    .map(CrewBasicDTO::toDTO)
                    .toList();
        };
    }
}
