package org.unito.postgreserver.studio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {
    private final StudioRepository studioRepository;

    @Autowired
    public StudioService(final StudioRepository studioRepository) { this.studioRepository = studioRepository; }
}
