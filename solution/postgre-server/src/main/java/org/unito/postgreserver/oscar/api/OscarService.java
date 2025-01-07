package org.unito.postgreserver.oscar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
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

    public List<Oscar> getCeremoniesWithFilter(OscarFilterDTO filter) {
        Specification<Oscar> spec = combineWithAnd(List.of(
        ));

        return oscarRepository.findAll();
    }
}

