package org.unito.postgreserver.release.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.release.dto.ReleaseBasicDTO;
import org.unito.postgreserver.release.dto.ReleaseFilterDTO;
import org.unito.postgreserver.release.model.Release;
import org.unito.postgreserver.release.model.ReleaseType;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.List;
import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.buildResponse;
import static org.unito.postgreserver.utils.ServiceCommon.setPageable;
import static org.unito.postgreserver.utils.SpecificationUtility.*;

@Service
public class ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) { this.releaseRepository = releaseRepository; }

    public Map<String, Object> getReleaseByFilters(GenericFilterDTO genericFilter, ReleaseFilterDTO releaseFilter) {
        Specification<Release> specification = combineWithAnd(List.of(
            equalsTo("country", releaseFilter.getCountry()),
            equalsTo("distributionFormat", releaseFilter.getDistributionFormat()),
            equalsTo("rating", releaseFilter.getRating())
        ));

        Pageable pageable = setPageable(genericFilter, releaseFilter.getSortBy());
        Page<Release> releasePage = releaseRepository.findAll(specification, pageable);
        List<? extends ReleaseType> releases = mapReleases(releasePage.getContent(), genericFilter.getResponseType());

        return buildResponse(genericFilter, releasePage.getTotalPages(), releases);
    }

    private List<? extends ReleaseType> mapReleases(List<Release> releases, GenericFilterDTO.Type responseType) {
        return switch (responseType) {
            case FULL -> releases;
            case BASIC -> releases.stream()
                    .map(ReleaseBasicDTO::toDTO)
                    .toList();
        };
    }
}
