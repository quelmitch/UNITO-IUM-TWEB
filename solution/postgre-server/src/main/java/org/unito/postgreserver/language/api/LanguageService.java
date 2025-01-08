package org.unito.postgreserver.language.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(final LanguageRepository languageRepository) { this.languageRepository = languageRepository; }

    public List<String> getAllLanguages() {
        return languageRepository.findDistinctLanguage();
    }
}
