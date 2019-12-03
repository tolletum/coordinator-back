package com.cdnator.coordinator.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import com.cdnator.coordinator.dao.ProfileDao;
import com.cdnator.coordinator.dao.entity.Profile;
import com.cdnator.coordinator.dto.ProfileDTO;
import com.cdnator.coordinator.mapper.MapperDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/coordinators")
public class ProfileController {

  @Autowired
  private ProfileDao dao;

  @Autowired
  private MapperDTO mapper;

  @PostMapping("/profiles")
  public ResponseEntity<ProfileDTO> insertProfile(@Valid @RequestBody ProfileDTO profile) {

    final Profile savedprofile = dao.insertProfile(mapper.profileDTOToProfile(profile));

    final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedprofile.getId()).toUri();

    return ResponseEntity.created(location).body(mapper.profileToProfileDTO(savedprofile));
  }

  @GetMapping("/profiles")
  public List<ProfileDTO> listProfiles() {

    final List<Profile> listOfProfiles = dao.listProfiles();

    return mapper.listProfileToProfileDTO(listOfProfiles);
  }

  @GetMapping("/profiles/{id}")
  public ResponseEntity<ProfileDTO> getProfile(@PathVariable UUID id) {

    final Profile profile = dao.getProfile(id);

    return ResponseEntity.ok().body(mapper.profileToProfileDTO(profile));
  }

  @PatchMapping("/profiles/{id}")
  public ResponseEntity<ProfileDTO> updateProfile(@PathVariable UUID id, @RequestBody ProfileDTO updatedProfile) {

    final Profile savedProfile = dao.updateProfile(id, mapper.profileDTOToProfile(updatedProfile));

    return ResponseEntity.ok().body(mapper.profileToProfileDTO(savedProfile));
  }

  @DeleteMapping("/profiles/{id}")
  public void deleteProfile(@PathVariable UUID id) {

    dao.deleteProfile(id);
    
  }
}