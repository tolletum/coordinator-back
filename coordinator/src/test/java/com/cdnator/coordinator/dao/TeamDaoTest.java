package com.cdnator.coordinator.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dao.repository.TeamRepository;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TeamDaoTest {

    @InjectMocks
    private TeamDao dao;

    @Mock
    private TeamRepository repository;

    @Test
    public void insertTeamTest() {

        Mockito.when(repository.save(Mockito.any())).thenReturn(getTeamntityMocked());

        Team savedTeam = dao.insertTeam(new Team());

        Assert.assertNotNull(savedTeam);
        Assert.assertNotNull(savedTeam.getId());
    }

    @Test
    public void listTeamsTest() {

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(getTeamntityMocked()));

        List<Team> teamsOut = dao.listTeams();

        Assert.assertNotNull(teamsOut);
        Assert.assertEquals(1, teamsOut.size());
    }

    @Test
    public void getTeamTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(getTeamntityMocked()));

        Team teamOut = dao.getTeam(UUID.randomUUID());

        Assert.assertNotNull(teamOut);
        Assert.assertNotNull(teamOut.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void getTeamDoesntExistErrorTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        dao.getTeam(UUID.randomUUID());

    }

    @Test
    public void updateTeamTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(getTeamntityMocked()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(getTeamntityMocked());

        Team teamIn = new Team();
        teamIn.setArea("area");
        teamIn.setDescription("description");

        Team savedTeam = dao.updateTeam(UUID.randomUUID(), teamIn);

        Assert.assertNotNull(savedTeam);
        Assert.assertNotNull(savedTeam.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void updateTeamDoesntExistTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        Team teamIn = new Team();
        teamIn.setArea("area");
        teamIn.setDescription("description");

        dao.updateTeam(UUID.randomUUID(), teamIn);

    }

    private Team getTeamntityMocked() {

        Team team = new Team();

        team.setId(UUID.randomUUID());
        team.setArea("area");
        team.setDescription("description");
        team.setId(UUID.randomUUID());

        Project project = new Project();

        project.setBudget(new BigDecimal(7));
        project.setClient("client");
        project.setDescription("description");
        project.setId("id");
        project.setManager("manager");
        project.setName("name");

        team.setProjects(Arrays.asList(project));

        Employee employee = new Employee();

        employee.setChargeability(50);
        employee.setId("E053434");
        employee.setIsCoordinator(false);
        employee.setLastName("lastName");
        employee.setName("name");

        team.setEmployees(Arrays.asList(employee));

        return team;
    }
    
}