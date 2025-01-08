package org.unito.postgreserver.language.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.language.model.Language;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {
    @Query("SELECT DISTINCT l.language FROM Language l")
    List<String> findDistinctLanguage();
}
