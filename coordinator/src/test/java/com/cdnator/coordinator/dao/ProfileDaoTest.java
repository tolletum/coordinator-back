package com.cdnator.coordinator.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.entity.Profile;
import com.cdnator.coordinator.dao.repository.ProfileRepository;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProfileDaoTest {

    @InjectMocks
    private ProfileDao dao;

    @Mock
    private ProfileRepository repository;

    @Test
    public void insertProfileTest() {

        Mockito.when(repository.save(Mockito.any())).thenReturn(getProfileEntityMocked());

        Profile savedProfile = dao.insertProfile(new Profile());

        Assert.assertNotNull(savedProfile);
        Assert.assertNotNull(savedProfile.getId());
    }

    @Test
    public void listProfilesTest() {

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(getProfileEntityMocked()));

        List<Profile> profilesOut = dao.listProfiles();

        Assert.assertNotNull(profilesOut);
        Assert.assertEquals(1, profilesOut.size());
    }

    @Test
    public void getProfileTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(getProfileEntityMocked()));

        Profile profileOut = dao.getProfile(UUID.randomUUID());

        Assert.assertNotNull(profileOut);
        Assert.assertNotNull(profileOut.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void getProfileDoesntExistErrorTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        dao.getProfile(UUID.randomUUID());

    }

    @Test
    public void updateProfileTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(getProfileEntityMocked()));
        Mockito.when(repository.save(Mockito.any())).thenReturn(getProfileEntityMocked());

        Profile profileIn = new Profile();
        profileIn.setDescription("description");
        profileIn.setRate(new BigDecimal(6));

        Profile savedProfile = dao.updateProfile(UUID.randomUUID(), profileIn);

        Assert.assertNotNull(savedProfile);
        Assert.assertNotNull(savedProfile.getId());
    }

    @Test(expected=EntityNotFoundException.class)
    public void updateProfileDoesntExistTest() {

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        Profile profileIn = new Profile();
        profileIn.setDescription("description");
        profileIn.setRate(new BigDecimal(6));

        dao.updateProfile(UUID.randomUUID(), profileIn);

    }

    private Profile getProfileEntityMocked() {

        Profile profile = new Profile();

        profile.setDescription("description");
        profile.setId(UUID.randomUUID());
        profile.setRate(new BigDecimal(5));

        Employee employee = new Employee();

        employee.setChargeability(50);
        employee.setId("E053434");
        employee.setIsCoordinator(false);
        employee.setLastName("lastName");
        employee.setName("name");
    
        profile.setEmployees(Arrays.asList(employee));

        return profile;
    }
    
}