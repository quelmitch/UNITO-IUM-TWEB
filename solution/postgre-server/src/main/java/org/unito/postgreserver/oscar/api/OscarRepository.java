package org.unito.postgreserver.oscar.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.unito.postgreserver.oscar.model.Oscar;

import java.util.List;

public interface OscarRepository extends JpaRepository<Oscar, Integer>, JpaSpecificationExecutor<Oscar> {
    @Query("SELECT DISTINCT o.category FROM Oscar o")
    List<String> findDistinctCategories();
}
