package com.cdnator.coordinator.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dao.repository.TeamRepository;
import com.cdnator.coordinator.exception.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamDao {

  private static final Logger logger = LoggerFactory.getLogger(TeamDao.class);

  @Autowired
  private TeamRepository repository;

  public Team insertTeam(Team team) {

    final Team savedTeam = repository.save(team);

    return savedTeam;
  }

  public List<Team> listTeams() {

    final List<Team> listOfTeams = repository.findAll();

    return listOfTeams;
  }

  public Team getTeam(UUID id) {

    final Optional<Team> team = repository.findById(id);

    if (team.isPresent()) {
      return team.get();
    } else {
      logger.error("ERROR: Method updateTeam(). Team not found id %s", id);
      throw new EntityNotFoundException("Team not found with id: " + id);
    }
  }

  public Team updateTeam(UUID id, Team updatedTeam) {

    final Optional<Team> existentTeam = repository.findById(id);

    if (!existentTeam.isPresent()) {
      logger.error("ERROR: Method updateTeam(). Team not found id %s", id);
      throw new EntityNotFoundException("Team not found with id: " + id);
    }

    if (updatedTeam.getDescription() != null) {
      existentTeam.get().setDescription(updatedTeam.getDescription());
    }

    if (updatedTeam.getArea() != null) {
      existentTeam.get().setArea(updatedTeam.getArea());
    }

    final Team savedTeam = repository.save(existentTeam.get());

    return savedTeam;
  }

  public void deleteTeam(UUID id) {

    repository.deleteById(id);
  }

}