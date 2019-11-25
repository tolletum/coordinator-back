package com.cdnator.coordinator.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import com.cdnator.coordinator.dao.ProjectDao;
import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dao.entity.Team;
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
public class ProjectControllerTest {

  @InjectMocks
  private ProjectController controller = new ProjectController();

  @Mock
  private ProjectDao dao;

  @Mock
  private MapperDTO mapper;

  MockHttpServletRequest request = new MockHttpServletRequest();

  @Before
  public void before() {

    this.request.setScheme("http");
    this.request.setServerName("localhost");
    this.request.setServerPort(8080);
    this.request.setRequestURI("/coordinators/projects");

    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));
  }

  @Test
  public void insertProjectTest() {

    Project projectDao = getProjectMocked();
    Mockito.when(dao.insertProject(Mockito.any())).thenReturn(projectDao);
    Mockito.when(mapper.projectToProjectDTO(Mockito.any())).thenReturn(new ProjectDTO());
    
    ResponseEntity<ProjectDTO> projectCreated = controller.insertProject(new ProjectDTO());

    
    Assert.assertNotNull(projectCreated);
    Assert.assertNotNull(projectCreated.getHeaders());
    Assert.assertNotNull(projectCreated.getHeaders().get("location"));
    Assert.assertEquals(projectCreated.getHeaders().get("location"),
        Arrays.asList("http://localhost:8080/coordinators/projects/" + projectDao.getId()));
    Assert.assertNotNull(projectCreated.getBody());
  }

  @Test
  public void listProjectsTest() {

    Mockito.when(dao.listProjects()).thenReturn(Arrays.asList(getProjectMocked()));
    Mockito.when(mapper.listProjectToProjectDTO(Mockito.anyList())).thenReturn(Arrays.asList(getProjectDTOMocked()));

    List<ProjectDTO> projectsOut = controller.listProjects();

    Assert.assertNotNull(projectsOut);
    Assert.assertEquals(projectsOut.size(), 1);

  }

  @Test
  public void getProjectTest() {

    Mockito.when(dao.getProject(Mockito.anyString())).thenReturn(getProjectMocked());
    Mockito.when(mapper.projectToProjectDTO(Mockito.any())).thenReturn(getProjectDTOMocked());

    ResponseEntity<ProjectDTO> projectOut = controller.getProject("id");

    Assert.assertNotNull(projectOut);
    Assert.assertNotNull(projectOut.getBody());

  }

  @Test
  public void updateProjectTest() {

    Mockito.when(dao.updateProject(Mockito.anyString(), Mockito.any())).thenReturn(getProjectMocked());
    Mockito.when(mapper.projectToProjectDTO(Mockito.any())).thenReturn(getProjectDTOMocked());

    ResponseEntity<ProjectDTO> projectOut = controller.updateProject("id", new ProjectDTO());

    Assert.assertNotNull(projectOut);
    Assert.assertNotNull(projectOut.getBody());

  }

  private Project getProjectMocked() {

    Project project = new Project();

    project.setId("id");
    project.setDescription("description");
    project.setBudget(new BigDecimal(0.0));
    project.setClient("client");
    project.setManager("manager");
    project.setName("name");
    Team team = new Team();
    team.setId(UUID.randomUUID());
    project.setTeam(team);    

    return project;
  }

  private ProjectDTO getProjectDTOMocked() {

    ProjectDTO project = new ProjectDTO();

    project.setId("id");
    project.setDescription("description");
    project.setBudget(new BigDecimal(0.0));
    project.setClient("client");
    project.setManager("manager");
    project.setName("name");
    TeamDTO team = new TeamDTO();
    team.setId(UUID.randomUUID());
    project.setTeam(team);    

    return project;
  }
}