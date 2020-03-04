package com.cdnator.coordinator.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.HoursQ;
import com.cdnator.coordinator.dao.entity.HoursQId;
import com.cdnator.coordinator.dao.entity.Project;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dao.repository.HoursQRepository;
import com.cdnator.coordinator.dao.repository.HoursQRepositoryCustom;
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
public class HoursQDaoTest {

    @InjectMocks
    private HoursQDao dao;

    @Mock
    private HoursQRepository repository;

    @Test
    public void insertHoursQTest() {

        Mockito.when(repository.save(Mockito.any())).thenReturn(getHoursQEntityMocked());

        HoursQ savedHoursQ = dao.insertHoursQ(new HoursQ());

        Assert.assertNotNull(savedHoursQ);
        Assert.assertNotNull(savedHoursQ.getId());
    }

    @Test
    public void listHoursQTest() {

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(getHoursQEntityMocked()));

        List<HoursQ> hoursQOut = dao.listHoursQ();

        Assert.assertNotNull(hoursQOut);
        Assert.assertEquals(1, hoursQOut.size());
    }

    @Test
    public void listHoursQByTeamAndQQTest() {

        Mockito.when(repository.listHoursQByTeamAndQ(Mockito.any(), Mockito.anyString(), Mockito.anyString())).thenReturn(Arrays.asList(getHoursQEntityMocked()));

        List<HoursQ> hoursQOut = dao.listHoursQByTeamAndQ(UUID.randomUUID(), "2020", "Q2");

        Assert.assertNotNull(hoursQOut);
        Assert.assertEquals(1, hoursQOut.size());
    }

    @Test
    public void getHoursQTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(getHoursQEntityMocked()));

        HoursQ hoursQOut = dao.getHoursQ(new HoursQId());

        Assert.assertNotNull(hoursQOut);
        Assert.assertNotNull(hoursQOut.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void getHoursQDoesntExistErrorTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        dao.getHoursQ(new HoursQId());

    }

    // @Test
    // public void updateTeamTest() {

    //     Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(getTeamntityMocked()));
    //     Mockito.when(repository.save(Mockito.any())).thenReturn(getTeamntityMocked());

    //     Team teamIn = new Team();
    //     teamIn.setArea("area");
    //     teamIn.setDescription("description");

    //     Team savedTeam = dao.updateTeam(UUID.randomUUID(), teamIn);

    //     Assert.assertNotNull(savedTeam);
    //     Assert.assertNotNull(savedTeam.getId());
    // }

    // @Test(expected=EntityNotFoundException.class)
    // public void updateTeamDoesntExistTest() {

    //     Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

    //     Team teamIn = new Team();
    //     teamIn.setArea("area");
    //     teamIn.setDescription("description");

    //     dao.updateTeam(UUID.randomUUID(), teamIn);

    // }

    private HoursQ getHoursQEntityMocked() {

        Employee employee = new Employee();
        employee.setId("E123456");

        HoursQId id = new HoursQId();
        id.setEmployee(employee);
        id.setMonth("MARZO");
        id.setQuarter("Q1");
        id.setYear("2020");

        HoursQ hoursQ = new HoursQ();
        hoursQ.setId(id);
        hoursQ.setHours(124);

        return hoursQ;
    }
    
}