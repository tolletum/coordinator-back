package com.cdnator.coordinator.controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.cdnator.coordinator.dao.EmployeeDao;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.Profile;
import com.cdnator.coordinator.dao.entity.Team;
import com.cdnator.coordinator.dto.EmployeeDTO;
import com.cdnator.coordinator.dto.ProfileDTO;
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
public class EmployeeControlerTest {

  @InjectMocks
  private EmployeeController controller = new EmployeeController();

  @Mock
  private EmployeeDao dao;

  @Mock
  private MapperDTO mapper;

  MockHttpServletRequest request = new MockHttpServletRequest();

  @Before
  public void before() {

    this.request.setScheme("http");
    this.request.setServerName("localhost");
    this.request.setServerPort(8080);
    this.request.setRequestURI("/coordinators/employees");

    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));
  }

  @Test
  public void insertEmployeeTest() {

    Employee employeeDao = getEmployeeMocked();
    Mockito.when(dao.insertEmployee(Mockito.any())).thenReturn(employeeDao);

    EmployeeDTO employeeIn = new EmployeeDTO();
    employeeIn.setName("name");
    employeeIn.setLastName("lastName");

    Mockito.when(mapper.employeeToEmployeeDTO(Mockito.any())).thenReturn(employeeIn);

    ResponseEntity<EmployeeDTO> employeeCreated = controller.insertEmployee(employeeIn);

    Assert.assertNotNull(employeeCreated);
    Assert.assertNotNull(employeeCreated.getHeaders());
    Assert.assertNotNull(employeeCreated.getHeaders().get("location"));
    Assert.assertEquals(employeeCreated.getHeaders().get("location"),
        Arrays.asList("http://localhost:8080/coordinators/employees/" + employeeDao.getId()));
    Assert.assertNotNull(employeeCreated.getBody());
  }

  @Test
  public void listEmployees() {

    Mockito.when(dao.listEmployees()).thenReturn(Arrays.asList(getEmployeeMocked()));
    Mockito.when(mapper.listEmployeeToEmployeeDTO(Mockito.anyList())).thenReturn(Arrays.asList(getEmployeeDTOMocked()));

    List<EmployeeDTO> employeesOut = controller.listEmployees();

    Assert.assertNotNull(employeesOut);
    Assert.assertEquals(employeesOut.size(), 1);

  }

  @Test
  public void getEmployee() {

    Mockito.when(dao.getEmployee(Mockito.anyString())).thenReturn(getEmployeeMocked());
    Mockito.when(mapper.employeeToEmployeeDTO(Mockito.any())).thenReturn(getEmployeeDTOMocked());

    ResponseEntity<EmployeeDTO> employeeOut = controller.getEmployee("id");

    Assert.assertNotNull(employeeOut);
    Assert.assertNotNull(employeeOut.getBody());

  }

  @Test
  public void updateEmployee() {

    Mockito.when(dao.updateEmployee(Mockito.anyString(), Mockito.any())).thenReturn(getEmployeeMocked());
    Mockito.when(mapper.employeeToEmployeeDTO(Mockito.any())).thenReturn(getEmployeeDTOMocked());

    EmployeeDTO employeeIn = new EmployeeDTO();
    employeeIn.setChargeability(60);
    employeeIn.setIsCoordinator(false);

    ResponseEntity<EmployeeDTO> employeeOut = controller.updateEmployee("id", employeeIn);

    Assert.assertNotNull(employeeOut);
    Assert.assertNotNull(employeeOut.getBody());

  }

  private Employee getEmployeeMocked() {

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

  private EmployeeDTO getEmployeeDTOMocked() {

    EmployeeDTO employee = new EmployeeDTO();

    employee.setChargeability(50);
    employee.setId("E053434");
    employee.setIsCoordinator(false);
    employee.setLastName("lastName");
    employee.setName("name");

    ProfileDTO profile = new ProfileDTO();
    profile.setId(UUID.randomUUID());
    employee.setProfile(profile);

    TeamDTO team = new TeamDTO();
    team.setId(UUID.randomUUID());
    employee.setTeam(team);

    return employee;
  }
}