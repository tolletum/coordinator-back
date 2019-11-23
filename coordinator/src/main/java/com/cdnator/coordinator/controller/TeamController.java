package com.cdnator.coordinator.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.cdnator.coordinator.dao.TeamDao;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dto.TeamDTO;
import com.cdnator.coordinator.mapper.MapperDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/coordinators")
public class TeamController {

  @Autowired
  private TeamDao dao;

  @Autowired
  private MapperDTO mapper;

  @PostMapping("/teams")
  public ResponseEntity<TeamDTO> insertTeam(@Valid @RequestBody TeamDTO team) {

    final Team savedTeam = dao.insertTeam(mapper.teamDTOToTeam(team));

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTeam.getId())
        .toUri();

    return ResponseEntity.created(location).body(mapper.teamToTeamDTO(savedTeam));
  }

  @GetMapping("/teams")
  public List<TeamDTO> listTeams() {

    final List<Team> listOfTeams = dao.listTeams();

    return mapper.listTeamToTeamDTO(listOfTeams);
  }

  @GetMapping("/teams/{id}")
  public ResponseEntity<TeamDTO> getTeam(@PathVariable String id) {

    final Team team = dao.getTeam(id);

    return ResponseEntity.ok().body(mapper.teamToTeamDTO(team));
  }

  @PatchMapping("/teams/{id}")
  public ResponseEntity<TeamDTO> updateTeam(@PathVariable String id, @RequestBody TeamDTO updatedTeam) {

    final Team savedTeam = dao.updateTeam(id, mapper.teamDTOToTeam(updatedTeam));

    return ResponseEntity.ok().body(mapper.teamToTeamDTO(savedTeam));
  }

}