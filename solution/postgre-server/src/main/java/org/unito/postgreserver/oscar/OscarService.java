package org.unito.postgreserver.oscar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.oscar.model.OscarCeremony;
import org.unito.postgreserver.oscar.model.OscarDTO;
import org.unito.postgreserver.oscar.model.OscarFilterDTO;
import org.unito.postgreserver.oscar.model.OscarNomination;

import java.util.List;
import java.util.stream.Collectors;

import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
class OscarService {
    private final OscarRepository oscarRepository;
    private final NominationRepository repo;
    // TODO: after refactoring leave only oscarRepository
    @Autowired
    public OscarService(OscarRepository oscarRepository, NominationRepository repo) {
        this.oscarRepository = oscarRepository;
        this.repo = repo;
    }

    public List<OscarDTO> getCeremoniesWithFilter(OscarFilterDTO filter) {
        System.out.println(filter.getCategories());
        Specification<OscarNomination> spec = combineWithAnd(List.of(
                joinAndLessThan("oscarCeremony", "year", filter.getYearLT()),
                joinAndGreaterThan("oscarCeremony", "year", filter.getYearGT()),
                equalsTo("winner", filter.getWinner()),
                equalsIn("category", filter.getCategories())
        ));

        return mapToDTO(repo.findAll(spec));
    }

    public List<OscarCeremony> getCeremoniesByMovie(String movie) {
        // TODO: merge with movie advanced search
        return oscarRepository.findCeremoniesByMovie(movie);
    }

    public List<OscarCeremony> getCeremoniesByPerson(String person) {
        List<OscarCeremony> ceremonies = oscarRepository.findCeremoniesByPerson(person);
        ceremonies.forEach(ceremony -> ceremony.setNominations(
            ceremony.getNominations().stream()
                .filter(nominee -> person.equals(nominee.getPerson()))
                .collect(Collectors.toList())
        ));
        return ceremonies;
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

