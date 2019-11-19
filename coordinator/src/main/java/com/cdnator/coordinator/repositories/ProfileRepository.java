package com.cdnator.coordinator.repositories;

import com.cdnator.coordinator.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    
}