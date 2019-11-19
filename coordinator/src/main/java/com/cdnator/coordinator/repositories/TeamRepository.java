package com.cdnator.coordinator.repositories;

import com.cdnator.coordinator.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TeamRepository
 */
public interface TeamRepository extends JpaRepository<Team, String>{

    
}
