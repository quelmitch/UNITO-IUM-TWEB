package org.unito.postgreserver.release.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) { this.releaseRepository = releaseRepository; }
}
