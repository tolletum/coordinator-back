package com.cdnator.coordinator.dao;

import java.util.List;
import java.util.Optional;

import com.cdnator.coordinator.dao.entity.Profile;
import com.cdnator.coordinator.dao.repository.ProfileRepository;
import com.cdnator.coordinator.exception.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileDao {

  private static final Logger logger = LoggerFactory.getLogger(ProfileDao.class);

  @Autowired
  private ProfileRepository repository;

  public Profile insertProfile(Profile profile) {

    final Profile savedprofile = repository.save(profile);

    return savedprofile;
  }

  public List<Profile> listProfiles() {

    final List<Profile> listOfProfiles = repository.findAll();

    return listOfProfiles;
  }

  public Profile getProfile(String id) {

    final Optional<Profile> profile = repository.findById(id);

    if (profile.isPresent()) {
      return profile.get();
    } else {
      logger.error("ERROR: method updateProfile(). Profile not found with id %s", id);
      throw new EntityNotFoundException("Profile not found with id: " + id);
    }
  }

  public Profile updateProfile(String id, Profile updatedProfile) {

    final Optional<Profile> existentProfile = repository.findById(id);
    if (!existentProfile.isPresent()) {
      logger.error("ERROR: method updateProfile(). Profile not found with id %s", id);
      throw new EntityNotFoundException("Profile not found with id: " + id);
    }

    if (updatedProfile.getDescription() != null) {
      existentProfile.get().setDescription(updatedProfile.getDescription());
    }

    if (updatedProfile.getRate() != null) {
      existentProfile.get().setRate(updatedProfile.getRate());
    }

    final Profile savedProfile = repository.save(existentProfile.get());

    return savedProfile;
  }

}