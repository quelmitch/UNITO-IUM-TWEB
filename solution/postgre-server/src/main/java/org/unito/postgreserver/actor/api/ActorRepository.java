package org.unito.postgreserver.actor.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.unito.postgreserver.actor.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long>, JpaSpecificationExecutor<Actor> {
}
