package com.cdnator.coordinator.dao.repository;

import java.util.List;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.HoursQ;

/**
 * HoursQRepository
 */
public interface HoursQRepositoryCustom {

    public List<HoursQ> listHoursQByTeamAndQ(UUID team, String year, String quarter);
}