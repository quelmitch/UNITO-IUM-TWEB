package org.unito.postgreserver.theme.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeService(final ThemeRepository themeRepository) { this.themeRepository = themeRepository; }
}
