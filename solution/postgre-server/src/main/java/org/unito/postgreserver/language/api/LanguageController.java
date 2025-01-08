package org.unito.postgreserver.language.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
public class LanguageController {
    private final LanguageService languageService;

    @Autowired
    public LanguageController(final LanguageService languageService) { this.languageService = languageService; }
}
