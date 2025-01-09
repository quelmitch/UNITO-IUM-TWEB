package org.unito.postgreserver.studio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.oscar.dto.OscarBasicDTO;
import org.unito.postgreserver.oscar.model.Oscar;
import org.unito.postgreserver.oscar.model.OscarType;
import org.unito.postgreserver.studio.dto.StudioBasicDTO;
import org.unito.postgreserver.studio.dto.StudioFilterDTO;
import org.unito.postgreserver.studio.model.Studio;
import org.unito.postgreserver.studio.model.StudioType;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.buildResponse;
import static org.unito.postgreserver.utils.ServiceCommon.setPageable;
import static org.unito.postgreserver.utils.SpecificationUtility.*;
import static org.unito.postgreserver.utils.SpecificationUtility.equalsTo;

@Service
public class StudioService {
    private final StudioRepository studioRepository;

    @Autowired
    public StudioService(final StudioRepository studioRepository) {this.studioRepository = studioRepository;}

    public Map<String, Object> getStudioByFilter(GenericFilterDTO genericFilter, StudioFilterDTO studioFilter) {
        Specification<Studio> specification = like("studio", studioFilter.getStudio());

        Pageable pageable = setPageable(genericFilter, studioFilter.getSortBy());
        Page<Studio> studioPage = studioRepository.findAll(specification, pageable);
        List<? extends StudioType> studios = mapStudios(studioPage.getContent(), genericFilter.getResponseType());

        return buildResponse(genericFilter, studioPage.getTotalPages(), studios);
    }

    private List<? extends StudioType> mapStudios(List<Studio> studios, GenericFilterDTO.Type responseType) {
        return switch (responseType) {
            case FULL -> studios;
            case BASIC -> studios.stream()
                .map(StudioBasicDTO::toDTO)
                .toList();
        };
    }

    public Map<String, Object> getAllStudios(GenericFilterDTO genericFilter) {
        Pageable pageable = setPageable(genericFilter, "studio");
        Page<String> studioPage = studioRepository.findDistinctStudio(pageable);

        return buildResponse(genericFilter, studioPage.getTotalPages(), studioPage.getContent());
    }
}
