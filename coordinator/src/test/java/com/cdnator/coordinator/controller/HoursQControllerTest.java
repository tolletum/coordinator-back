package com.cdnator.coordinator.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.cdnator.coordinator.dao.HoursQDao;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.HoursQ;
import com.cdnator.coordinator.dao.entity.HoursQId;
import com.cdnator.coordinator.dto.EmployeeDTO;
import com.cdnator.coordinator.dto.HoursQDTO;
import com.cdnator.coordinator.dto.HoursQIdDTO;
import com.cdnator.coordinator.dto.enumtypes.MonthEnum;
import com.cdnator.coordinator.dto.enumtypes.QuarterEnum;
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
public class HoursQControllerTest {

  @InjectMocks
  private HoursQController controller = new HoursQController();

  @Mock
  private HoursQDao dao;

  @Mock
  private MapperDTO mapper;

  MockHttpServletRequest request = new MockHttpServletRequest();

  @Before
  public void before() {

    this.request.setScheme("http");
    this.request.setServerName("localhost");
    this.request.setServerPort(8080);
    this.request.setRequestURI("/coordinators/hours-quarter");

    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));
  }

  @Test
  public void insertHoursQTest() throws UnsupportedEncodingException {

    HoursQ hoursQDao = getHoursQMocked();
    Mockito.when(dao.insertHoursQ(Mockito.any())).thenReturn(hoursQDao);

    Mockito.when(mapper.hoursQToHoursQDTO(Mockito.any())).thenReturn(getHoursQDTOMocked());

    ResponseEntity<HoursQDTO> hoursQCreated = controller.insertHoursQ(getHoursQDTOMocked());

    Assert.assertNotNull(hoursQCreated);
    Assert.assertNotNull(hoursQCreated.getHeaders());
    Assert.assertNotNull(hoursQCreated.getHeaders().get("location"));
    Assert.assertEquals(hoursQCreated.getHeaders().get("location"),
        Arrays.asList(new StringBuffer("http://localhost:8080/coordinators/hours-quarter/")
          .append(hoursQDao.getId().getYear()).append("-")
          .append(hoursQDao.getId().getQuarter()).append("-")
          .append(hoursQDao.getId().getMonth()).append("-")
          .append(hoursQDao.getId().getEmployee().getId())
          .toString()));
    Assert.assertNotNull(hoursQCreated.getBody());
  }

  @Test
  public void listHoursQTest() {

    Mockito.when(dao.listHoursQ()).thenReturn(Arrays.asList(getHoursQMocked()));
    Mockito.when(mapper.listHoursQToHoursQDTO(Mockito.anyList())).thenReturn(Arrays.asList(getHoursQDTOMocked()));

    List<HoursQDTO> hoursQOut = controller.listHoursQByTeamAndQ(null, null, null);

    Assert.assertNotNull(hoursQOut);
    Assert.assertEquals(hoursQOut.size(), 1);

  }

  @Test
  public void listHoursQByTeamAndQuarterTest() {

    Mockito.when(dao.listHoursQByTeamAndQ(Mockito.any(), Mockito.anyString(), Mockito.anyString())).thenReturn(Arrays.asList(getHoursQMocked()));
    Mockito.when(mapper.listHoursQToHoursQDTO(Mockito.anyList())).thenReturn(Arrays.asList(getHoursQDTOMocked()));

    List<HoursQDTO> hoursQOut = controller.listHoursQByTeamAndQ(UUID.randomUUID(), "2020", "Q2");

    Assert.assertNotNull(hoursQOut);
    Assert.assertEquals(hoursQOut.size(), 1);

  }

  @Test
  public void getHoursQTest() {

    Mockito.when(dao.getHoursQ(Mockito.any())).thenReturn(getHoursQMocked());
    Mockito.when(mapper.hoursQToHoursQDTO(Mockito.any())).thenReturn(getHoursQDTOMocked());

    ResponseEntity<HoursQDTO> hoursQOut = controller.getHoursQ("2020", "Q2","MARZO","E123456");

    Assert.assertNotNull(hoursQOut);
    Assert.assertNotNull(hoursQOut.getBody());

  }

  // @Test
  // public void updateEmployeeTest() {

  //   Mockito.when(dao.updateEmployee(Mockito.anyString(), Mockito.any())).thenReturn(getEmployeeMocked());
  //   Mockito.when(mapper.employeeToEmployeeDTO(Mockito.any())).thenReturn(getEmployeeDTOMocked());

  //   EmployeeDTO employeeIn = new EmployeeDTO();
  //   employeeIn.setChargeability(60);
  //   employeeIn.setIsCoordinator(false);

  //   ResponseEntity<EmployeeDTO> employeeOut = controller.updateEmployee("id", employeeIn);

  //   Assert.assertNotNull(employeeOut);
  //   Assert.assertNotNull(employeeOut.getBody());

  // }

  private HoursQ getHoursQMocked() {

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

  private HoursQDTO getHoursQDTOMocked() {

    EmployeeDTO employee = new EmployeeDTO();
    employee.setId("E123456");

    HoursQIdDTO id = new HoursQIdDTO();
    id.setEmployee(employee);
    id.setMonth(MonthEnum.MARZO);
    id.setQuarter(QuarterEnum.Q1);
    id.setYear("2020");

    HoursQDTO hoursQ = new HoursQDTO();
    hoursQ.setId(id);
    hoursQ.setHours(124);

    return hoursQ;
}
}