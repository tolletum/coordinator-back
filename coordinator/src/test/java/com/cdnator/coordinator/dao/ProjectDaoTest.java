package com.cdnator.coordinator.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dao.repository.ProjectRepository;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProjectDaoTest {

    @InjectMocks
    private ProjectDao dao;

    @Mock
    private ProjectRepository repository;

    @Test
    public void insertProjectTest() {

        Mockito.when(repository.save(Mockito.any())).thenReturn(getProjectEntityMocked());

        Project savedProject = dao.insertProject(new Project());

        Assert.assertNotNull(savedProject);
        Assert.assertNotNull(savedProject.getId());
    }

    @Test
    public void listProjectsTest() {

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(getProjectEntityMocked()));

        List<Project> projectsOut = dao.listProjects();

        Assert.assertNotNull(projectsOut);
        Assert.assertEquals(1, projectsOut.size());
    }

    @Test
    public void getProjectTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(getProjectEntityMocked()));

        Project projectOut = dao.getProject("id");

        Assert.assertNotNull(projectOut);
        Assert.assertNotNull(projectOut.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void getProjectDoesntExistErrorTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        dao.getProject("id");

    }

    @Test
    public void updateProjectTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(getProjectEntityMocked()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(getProjectEntityMocked());

        Project projectIn = new Project();
        projectIn.setBudget(new BigDecimal(5));
        projectIn.setDescription("description");
        projectIn.setClient("client");
        projectIn.setManager("manager");

        Project savedProject = dao.updateProject("id", projectIn);

        Assert.assertNotNull(savedProject);
        Assert.assertNotNull(savedProject.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void updateProjectDoesntExistTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        Project projectIn = new Project();
        projectIn.setBudget(new BigDecimal(5));
        projectIn.setDescription("description");
        projectIn.setClient("client");
        projectIn.setManager("manager");

        dao.updateProject("id", projectIn);

    }

    private Project getProjectEntityMocked() {

        Project project = new Project();

        project.setBudget(new BigDecimal(7));
        project.setClient("client");
        project.setDescription("description");
        project.setId("id");
        project.setManager("manager");
        project.setName("name");

        Team team = new Team();
        team.setId(UUID.randomUUID());

        project.setTeam(team);

        return project;
    }
    
}