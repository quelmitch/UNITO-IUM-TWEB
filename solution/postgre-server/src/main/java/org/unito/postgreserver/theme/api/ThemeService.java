package org.unito.postgreserver.theme.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeService(final ThemeRepository themeRepository) { this.themeRepository = themeRepository; }

    public List<String> getAllThemes() {
        return themeRepository.findDistinctTheme();
    }
}
