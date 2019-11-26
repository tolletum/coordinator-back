package com.cdnator.coordinator.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.Profile;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dao.repository.EmployeeRepository;
import com.cdnator.coordinator.exception.BadRequestException;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoTest {

    @InjectMocks
    private EmployeeDao dao;

    @Mock
    private EmployeeRepository repository;

    @Test
    public void insertEmployeeTest() {

        Mockito.when(repository.findEmployeeByNameAndLastName(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(getEmployeeEntityMocked());

        Employee employeeIn = new Employee();
        employeeIn.setName("name");
        employeeIn.setLastName("lastName");
        Employee savedEmployee = dao.insertEmployee(employeeIn);

        Assert.assertNotNull(savedEmployee);
        Assert.assertEquals("E053434", savedEmployee.getId());
    }

    @Test(expected=BadRequestException.class)
    public void insertEmployeeErrorExistsTest() {

        Mockito.when(repository.findEmployeeByNameAndLastName(Mockito.anyString(), Mockito.anyString())).thenReturn(new Employee());

        Employee employeeIn = new Employee();
        employeeIn.setName("name");
        employeeIn.setLastName("lastName");
        
        dao.insertEmployee(employeeIn);

    }

    @Test
    public void listEmployeesTest() {

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(getEmployeeEntityMocked()));

        List<Employee> employeesOut = dao.listEmployees();

        Assert.assertNotNull(employeesOut);
        Assert.assertEquals(1, employeesOut.size());
    }

    @Test
    public void getEmployeeTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(getEmployeeEntityMocked()));

        Employee employeeOut = dao.getEmployee("id");

        Assert.assertNotNull(employeeOut);
        Assert.assertEquals("E053434", employeeOut.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void getEmployeeDoesntExistErrorTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        dao.getEmployee("id");

    }

    @Test
    public void updateEmployeeTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(getEmployeeEntityMocked()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(getEmployeeEntityMocked());

        Employee employeeIn = new Employee();
        employeeIn.setChargeability(5);
        employeeIn.setIsCoordinator(true);
        employeeIn.setProfile(new Profile());

        Employee savedEmployee = dao.updateEmployee("id", employeeIn);

        Assert.assertNotNull(savedEmployee);
        Assert.assertEquals("E053434", savedEmployee.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void updateEmployeeDoesntExistTest() {

        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        Employee employeeIn = new Employee();
        employeeIn.setChargeability(5);
        employeeIn.setIsCoordinator(true);
        employeeIn.setProfile(new Profile());

        dao.updateEmployee("id", employeeIn);

    }

    private Employee getEmployeeEntityMocked() {

        Employee employee = new Employee();

        employee.setChargeability(50);
        employee.setId("E053434");
        employee.setIsCoordinator(false);
        employee.setLastName("lastName");
        employee.setName("name");
    
        Profile profile = new Profile();
        profile.setId(UUID.randomUUID());
        employee.setProfile(profile);
    
        Team team = new Team();
        team.setId(UUID.randomUUID());
        employee.setTeam(team);
    
        return employee;
    }
    
}