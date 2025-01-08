package org.unito.postgreserver.release.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.release.model.Release;

public interface ReleaseRepository extends JpaRepository<Release, Long>, JpaSpecificationExecutor<Release> {
}
