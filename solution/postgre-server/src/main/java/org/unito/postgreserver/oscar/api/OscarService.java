package org.unito.postgreserver.oscar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.oscar.dto.OscarDTO;
import org.unito.postgreserver.oscar.dto.OscarFilterDTO;
import org.unito.postgreserver.oscar.model.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
class OscarService {
    private final OscarRepository oscarRepository;

    @Autowired
    public OscarService(OscarRepository oscarRepository) {
        this.oscarRepository = oscarRepository;
    }

    public List<OscarDTO> getCeremoniesWithFilter(OscarFilterDTO filter) {
        Specification<OscarNomination> spec = combineWithAnd(List.of(
            lessThan("oscarCeremony", "year", filter.getYearLT()),
            greaterThan("oscarCeremony", "year", filter.getYearGT()),
            equalsTo("winner", filter.getWinner()),
            equalsIn("category", filter.getCategories()),
            like("movie", filter.getMovie()),
            like("person", filter.getPerson())
        ));

        return mapToDTO(oscarRepository.findAll(spec));
    }

    private List<OscarDTO> mapToDTO(List<OscarNomination> nominations) {
        return nominations.stream()
            .collect(Collectors.groupingBy(OscarNomination::getOscarCeremony))
            .entrySet().stream()
            .map(entry -> new OscarDTO(
                entry.getKey(),
                entry.getValue().stream()
                    .map(nomination -> new OscarDTO.nominationDTO(
                        nomination.getId(),
                        nomination.getCategory(),
                        nomination.getMovie(),
                        nomination.getPerson(),
                        nomination.isWinner()))
                    .collect(Collectors.toList())))
            .collect(Collectors.toList());
    }
}

