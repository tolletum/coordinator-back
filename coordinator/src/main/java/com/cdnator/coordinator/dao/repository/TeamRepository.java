package com.cdnator.coordinator.dao.repository;

import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TeamRepository
 */
public interface TeamRepository extends JpaRepository<Team, UUID> {

}
