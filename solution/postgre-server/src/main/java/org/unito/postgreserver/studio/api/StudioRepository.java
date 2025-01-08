package org.unito.postgreserver.studio.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.studio.model.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long>, JpaSpecificationExecutor<Studio> {
}
