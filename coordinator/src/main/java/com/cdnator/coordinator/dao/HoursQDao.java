package com.cdnator.coordinator.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.HoursQ;
import com.cdnator.coordinator.dao.entity.HoursQId;
import com.cdnator.coordinator.dao.repository.HoursQRepository;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HoursQDao {

  private static final Logger logger = LoggerFactory.getLogger(HoursQDao.class);

  @Autowired
  private HoursQRepository repository;

  public HoursQ insertHoursQ(HoursQ hoursQ) {

    final HoursQ savedHoursQ = repository.save(hoursQ);

    return savedHoursQ;
  }

  public List<HoursQ> listHoursQ() {

    final List<HoursQ> listOfHoursQ = repository.findAll();

    return listOfHoursQ;
  }

  public HoursQ getHoursQ(HoursQId id) {

    final Optional<HoursQ> hoursQ = repository.findById(id);

    if (hoursQ.isPresent()) {
      return hoursQ.get();
    } else {
      logger.error("ERROR: Method getHoursQ(). Hours by Q not found id %s", id);
      throw new EntityNotFoundException("HoursQ not found with id: " + id);
    }
  }

  public List<HoursQ> listHoursQByTeamAndQ(UUID teamId, String year, String quarter) {

    final List<HoursQ> hoursQ = repository.listHoursQByTeamAndQ(teamId, year, quarter);

    if (hoursQ.isEmpty()) {
      logger.error("ERROR: Method listHoursQByTeamAndQ(). Hours by Q not found team %s, year %s, quarter %s", teamId, year, quarter);
      throw new EntityNotFoundException("HoursQ not found with team: " + teamId + ", year: " + year + ", quarter: " + quarter);
    }

    return hoursQ;
  }

  public HoursQ updateHoursQ(HoursQId id, HoursQ updatedHoursQ) {

    final Optional<HoursQ> existentHoursQ = repository.findById(id);

    if (!existentHoursQ.isPresent()) {
      logger.error("ERROR: Method updateHoursQ(). Hours by Q not found id %s", id);
      throw new EntityNotFoundException("HoursQ not found with id: " + id);
    }

    final HoursQ savedHoursQ = repository.save(existentHoursQ.get());

    return savedHoursQ;
  }

  public void deleteHoursQ(HoursQId id) {

    repository.deleteById(id);
  }

}