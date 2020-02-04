package com.cdnator.coordinator.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.cdnator.coordinator.dao.TeamDao;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dto.EmployeeDTO;
import com.cdnator.coordinator.dto.ProjectDTO;
import com.cdnator.coordinator.dto.TeamDTO;
import com.cdnator.coordinator.mapper.MapperDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RunWith(MockitoJUnitRunner.class)
public class TeamControllerTest {

  @InjectMocks
  private TeamController controller = new TeamController();

  @Mock
  private TeamDao dao;

  @Mock
  private MapperDTO mapper;

  MockHttpServletRequest request = new MockHttpServletRequest();

  @Before
  public void before() {

    this.request.setScheme("http");
    this.request.setServerName("localhost");
    this.request.setServerPort(8080);
    this.request.setRequestURI("/coordinators/teams");

    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));
  }

  @Test
  public void insertTeamTest() {

    Team teamDao = getTeamMocked();
    Mockito.when(dao.insertTeam(Mockito.any())).thenReturn(teamDao);
    Mockito.when(mapper.teamToTeamDTO(Mockito.any())).thenReturn(new TeamDTO());
    
    ResponseEntity<TeamDTO> teamCreated = controller.insertTeam(new TeamDTO());

    
    Assert.assertNotNull(teamCreated);
    Assert.assertNotNull(teamCreated.getHeaders());
    Assert.assertNotNull(teamCreated.getHeaders().get("location"));
    Assert.assertEquals(teamCreated.getHeaders().get("location"),
        Arrays.asList("http://localhost:8080/coordinators/teams/" + teamDao.getId()));
    Assert.assertNotNull(teamCreated.getBody());
  }

  @Test
  public void listTeamsTest() {

    Mockito.when(dao.listTeams()).thenReturn(Arrays.asList(getTeamMocked()));
    Mockito.when(mapper.listTeamToTeamDTO(Mockito.anyList())).thenReturn(Arrays.asList(getTeamDTOMocked()));

    List<TeamDTO> teamsOut = controller.listTeams();

    Assert.assertNotNull(teamsOut);
    Assert.assertEquals(teamsOut.size(), 1);

  }

  @Test
  public void getTeamTest() {

    Mockito.when(dao.getTeam(Mockito.any())).thenReturn(getTeamMocked());
    Mockito.when(mapper.teamToTeamDTO(Mockito.any())).thenReturn(getTeamDTOMocked());

    ResponseEntity<TeamDTO> teamOut = controller.getTeam(UUID.randomUUID());

    Assert.assertNotNull(teamOut);
    Assert.assertNotNull(teamOut.getBody());

  }

  @Test
  public void updateTeamTest() {

    Mockito.when(dao.updateTeam(Mockito.any(), Mockito.any())).thenReturn(getTeamMocked());
    Mockito.when(mapper.teamToTeamDTO(Mockito.any())).thenReturn(getTeamDTOMocked());

    ResponseEntity<TeamDTO> teamOut = controller.updateTeam(UUID.randomUUID(), new TeamDTO());

    Assert.assertNotNull(teamOut);
    Assert.assertNotNull(teamOut.getBody());

  }

  private Team getTeamMocked() {

    Team team = new Team();

    team.setId(UUID.randomUUID());
    team.setArea("area");
    team.setDescription("description");
    team.setEmployees(Arrays.asList(new Employee()));
    team.setProjects(Arrays.asList(new Project()));

    return team;
  }

  private TeamDTO getTeamDTOMocked() {

    TeamDTO team = new TeamDTO();

    team.setId(UUID.randomUUID());
    team.setArea("area");
    team.setDescription("description");
    team.setEmployees(Arrays.asList(new EmployeeDTO()));
    team.setProjects(Arrays.asList(new ProjectDTO()));

    return team;
  }
}