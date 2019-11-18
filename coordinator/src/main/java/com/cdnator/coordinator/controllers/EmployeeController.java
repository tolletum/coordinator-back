package com.cdnator.coordinator.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.cdnator.coordinator.entities.Employee;
import com.cdnator.coordinator.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/coordinators")
public class EmployeeController {

  @Autowired
  EmployeeRepository repository;

  @PostMapping("/employees")
  public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee) {

    Employee savedEmployee = repository.save(employee);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId())
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/employees")
  public List<Employee> listEmployees() {

    List<Employee> listOfEmployees = repository.findAll();

    return listOfEmployees;
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployee(@PathVariable String id) {

    Optional<Employee> employee = repository.findById(id);

    if (employee.isPresent()) {
      return ResponseEntity.ok().body(employee.get());
    } else {
      return null;
    }
  }
}