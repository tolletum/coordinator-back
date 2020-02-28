package com.cdnator.coordinator.dao.repository;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.cdnator.coordinator.dao.entity.HoursQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HoursQRepositoryImpl
 */
public class HoursQRepositoryImpl implements HoursQRepositoryCustom {

  private static final Logger logger = LoggerFactory.getLogger(HoursQRepositoryImpl.class);

    @PersistenceContext
    EntityManager entityManager;

  @Override
  @SuppressWarnings("unchecked")
  public List<HoursQ> getHoursQByTeamAndQ(UUID team, String year, String quarter) {
    final Query query = entityManager.createNativeQuery(
        "SELECT q.year as year, q.quarter as quarter, q.month as month, q.employeeid as employeeid, q.hours as hours from coordinator.hours_q as q inner join coordinator.employee as e on e.id = q.employeeid where e.teamid = ? and q.year = ? and q.quarter = ?", "HoursQMapping");
    query.setParameter(1, team);
    query.setParameter(2, year);
    query.setParameter(3, quarter);

    logger.info("Consulta: " + query.toString());
    final List<HoursQ> hoursQ = (List<HoursQ>) query.getResultList();
    
    return hoursQ;
  }
    
}