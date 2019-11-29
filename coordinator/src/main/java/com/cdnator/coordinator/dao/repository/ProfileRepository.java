package com.cdnator.coordinator.dao.repository;

import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

}