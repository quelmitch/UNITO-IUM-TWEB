package org.unito.postgreserver.theme.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.theme.model.Theme;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long>, JpaSpecificationExecutor<Theme> {
    @Query("SELECT DISTINCT t.theme FROM Theme t")
    List<String> findDistinctTheme();
}
