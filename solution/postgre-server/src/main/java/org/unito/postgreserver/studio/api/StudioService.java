package org.unito.postgreserver.studio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.unito.postgreserver.studio.dto.StudioFilterDTO;
import org.unito.postgreserver.utils.GenericFilterDTO;

import java.util.Map;

import static org.unito.postgreserver.utils.ServiceCommon.buildResponse;
import static org.unito.postgreserver.utils.ServiceCommon.setPageable;

@Service
public class StudioService {
    private final StudioRepository studioRepository;

    @Autowired
    public StudioService(final StudioRepository studioRepository) { this.studioRepository = studioRepository; }

    public Map<String, Object> getAllStudios(GenericFilterDTO genericFilter, StudioFilterDTO studioFilter) {

        Pageable pageable = setPageable(genericFilter, "studio");
        Page<String> studioPage;
        if (studioFilter.getStudio() != null)
            studioPage = studioRepository.findDistinctStudio("%"+studioFilter.getStudio()+"%", pageable);
        else
            studioPage = studioRepository.findDistinctStudio(pageable);

        return buildResponse(genericFilter, studioPage.getTotalPages(), studioPage.getContent());
    }
}
