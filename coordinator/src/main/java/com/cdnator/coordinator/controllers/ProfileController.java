package com.cdnator.coordinator.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.cdnator.coordinator.entities.Profile;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import com.cdnator.coordinator.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ProfileRepository repository;

    @PostMapping("/profiles")
    public ResponseEntity<Profile> insertProfile(@Valid @RequestBody Profile profile) {
  
      final Profile savedprofile = repository.save(profile);
  
      final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(savedprofile.getId()).toUri();
  
      return ResponseEntity.created(location).body(savedprofile);
    }
  
    @GetMapping("/profiles")
    public List<Profile> listProfiles() {
  
      final List<Profile> listOfProfiles = repository.findAll();
  
      return listOfProfiles;
    }
  
    @GetMapping("/profiles/{id}")
    public ResponseEntity<Profile> getprofile(@PathVariable String id) {
  
      final Optional<Profile> profile = repository.findById(id);
  
      if (profile.isPresent()) {
        return ResponseEntity.ok().body(profile.get());
      } else {
        throw new EntityNotFoundException("Profile not found with id: " + id);
      }
    }
  
    @PatchMapping("/profiles/{id}")
    public ResponseEntity<Profile> updateprofile(@PathVariable String id, @RequestBody Profile updatedProfile) {
  
      final Optional<Profile> existentProfile = repository.findById(id);
      if (!existentProfile.isPresent()) {
        throw new EntityNotFoundException("Profile not found with id: " + id);
      }
  
      if(updatedProfile.getDescription() != null) {
        existentProfile.get().setDescription(updatedProfile.getDescription());
      }
  
      if(updatedProfile.getRate() != null) {
        existentProfile.get().setRate(updatedProfile.getRate());
      }
      
      final Profile savedProfile = repository.save(existentProfile.get());
  
      return ResponseEntity.ok().body(savedProfile);
    }
      
}