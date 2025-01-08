package org.unito.postgreserver.language.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.language.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {
}
