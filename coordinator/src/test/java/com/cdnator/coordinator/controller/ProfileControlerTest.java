package com.cdnator.coordinator.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.cdnator.coordinator.dao.ProfileDao;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.Profile;
import com.cdnator.coordinator.dto.EmployeeDTO;
import com.cdnator.coordinator.dto.ProfileDTO;
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
public class ProfileControlerTest {

  @InjectMocks
  private ProfileController controller = new ProfileController();

  @Mock
  private ProfileDao dao;

  @Mock
  private MapperDTO mapper;

  MockHttpServletRequest request = new MockHttpServletRequest();

  @Before
  public void before() {

    this.request.setScheme("http");
    this.request.setServerName("localhost");
    this.request.setServerPort(8080);
    this.request.setRequestURI("/coordinators/profiles");

    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));
  }

  @Test
  public void insertProfileTest() {

    Profile profileDao = getProfileMocked();
    Mockito.when(dao.insertProfile(Mockito.any())).thenReturn(profileDao);

    ResponseEntity<ProfileDTO> profileCreated = controller.insertProfile(new ProfileDTO());

    Assert.assertNotNull(profileCreated);
    Assert.assertNotNull(profileCreated.getHeaders());
    Assert.assertNotNull(profileCreated.getHeaders().get("location"));
    Assert.assertEquals(profileCreated.getHeaders().get("location"),
        Arrays.asList("http://localhost:8080/coordinators/profiles/" + profileDao.getId()));
    Assert.assertNotNull(profileCreated.getBody());
  }

  @Test
  public void listProfiles() {

    Mockito.when(dao.listProfiles()).thenReturn(Arrays.asList(getProfileMocked()));
    Mockito.when(mapper.listProfileToProfileDTO(Mockito.anyList())).thenReturn(Arrays.asList(getProfileDTOMocked()));

    List<ProfileDTO> ProfilesOut = controller.listProfiles();

    Assert.assertNotNull(ProfilesOut);
    Assert.assertEquals(ProfilesOut.size(), 1);

  }

  @Test
  public void getProfile() {

    Mockito.when(dao.getProfile(Mockito.anyString())).thenReturn(getProfileMocked());
    Mockito.when(mapper.profileToProfileDTO(Mockito.any())).thenReturn(getProfileDTOMocked());

    ResponseEntity<ProfileDTO> ProfileOut = controller.getProfile("id");

    Assert.assertNotNull(ProfileOut);
    Assert.assertNotNull(ProfileOut.getBody());

  }

  @Test
  public void updateProfile() {

    Mockito.when(dao.updateProfile(Mockito.anyString(), Mockito.any())).thenReturn(getProfileMocked());
    Mockito.when(mapper.profileToProfileDTO(Mockito.any())).thenReturn(getProfileDTOMocked());

    ProfileDTO profileIn = new ProfileDTO();
    profileIn.setDescription("description");
    profileIn.setRate(new BigDecimal(0.0));

    ResponseEntity<ProfileDTO> ProfileOut = controller.updateProfile("id", profileIn);

    Assert.assertNotNull(ProfileOut);
    Assert.assertNotNull(ProfileOut.getBody());

  }

  private Profile getProfileMocked() {

    Profile profile = new Profile();

    profile.setId(UUID.randomUUID());
    profile.setDescription("description");
    profile.setRate(new BigDecimal(0.0));

    Employee employee = new Employee();
    employee.setId("E040404");

    profile.setEmployees(Arrays.asList(employee));

    return profile;
  }

  private ProfileDTO getProfileDTOMocked() {

    ProfileDTO profile = new ProfileDTO();

    profile.setId(UUID.randomUUID());
    profile.setDescription("description");
    profile.setRate(new BigDecimal(0.0));

    EmployeeDTO employee = new EmployeeDTO();
    employee.setId("E040404");

    profile.setEmployees(Arrays.asList(employee));

    return profile;
  }
}