package org.unito.postgreserver.theme.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(final ThemeService themeService) { this.themeService = themeService; }

    @GetMapping("")
    public List<String> getAllThemes() {
        return themeService.getAllThemes();
    }
}
