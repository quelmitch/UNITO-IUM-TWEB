package org.unito.postgreserver.oscar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.oscar.dto.OscarBasicDTO;
import org.unito.postgreserver.oscar.dto.OscarFilterDTO;
import org.unito.postgreserver.oscar.model.Oscar;
import org.unito.postgreserver.oscar.model.OscarType;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.buildResponse;
import static org.unito.postgreserver.utils.ServiceCommon.setPageable;
import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
class OscarService {
    private final OscarRepository oscarRepository;

    @Autowired
    public OscarService(OscarRepository oscarRepository) {
        this.oscarRepository = oscarRepository;
    }

    public Map<String, Object> getOscarByFilters(GenericFilterDTO genericFilter, OscarFilterDTO oscarFilter) {
        Specification<Oscar> specification = combineWithAnd(List.of(
            greaterThan("numberCeremony", oscarFilter.getNumberCeremonyGT()),
            lessThan("numberCeremony", oscarFilter.getNumberCeremonyLT()),
            greaterThan("yearCeremony", oscarFilter.getYearCeremonyGT()),
            lessThan("yearCeremony", oscarFilter.getYearCeremonyLT()),
            equalsTo("category", oscarFilter.getCategory()),
            equalsTo("nomineeName", oscarFilter.getNomineeName()),
            equalsTo("nomineeMovie", oscarFilter.getNomineeMovie()),
            equalsTo("isWinner", oscarFilter.getIsWinner())
        ));

        Pageable pageable = setPageable(genericFilter, oscarFilter.getSortBy());
        Page<Oscar> oscarPage = oscarRepository.findAll(specification, pageable);
        List<? extends OscarType> oscars = mapOscars(oscarPage.getContent(), genericFilter.getResponseType());

        return buildResponse(genericFilter, oscarPage.getTotalPages(), oscars);
    }

    private List<? extends OscarType> mapOscars(List<Oscar> oscars, GenericFilterDTO.Type responseType) {
        return switch (responseType) {
            case FULL -> oscars;
            case BASIC -> oscars.stream()
                    .map(OscarBasicDTO::toDTO)
                    .toList();
        };
    }
}
