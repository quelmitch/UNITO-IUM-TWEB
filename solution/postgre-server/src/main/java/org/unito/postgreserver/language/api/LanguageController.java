package org.unito.postgreserver.language.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language")
@Tag(name = "Languages")
public class LanguageController {
    private final LanguageService languageService;

    @Autowired
    public LanguageController(final LanguageService languageService) { this.languageService = languageService; }

    @GetMapping("")
    public List<String> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}
