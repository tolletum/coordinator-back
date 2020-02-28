package com.cdnator.coordinator.dao.repository;

import com.cdnator.coordinator.dao.entity.HoursQ;
import com.cdnator.coordinator.dao.entity.HoursQId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * HoursQRepository
 */
public interface HoursQRepository extends JpaRepository<HoursQ, HoursQId>, HoursQRepositoryCustom {

}