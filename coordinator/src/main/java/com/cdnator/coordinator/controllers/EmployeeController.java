package com.cdnator.coordinator.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.cdnator.coordinator.entities.Employee;
import com.cdnator.coordinator.exception.BadRequestException;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import com.cdnator.coordinator.repositories.EmployeeRepository;
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

//TODO: Hacer el delete

@RestController
@RequestMapping("/coordinators")
public class EmployeeController {

  @Autowired
  private EmployeeRepository repository;

  @PostMapping("/employees")
  public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee) {

    // TODO: Al dar de alta un empleado, hay que verificar que no existe ya un
    // coordinador para el equipo seleccionado

    employee.setName(employee.getName().toUpperCase());
    employee.setLastName(employee.getLastName().toUpperCase());

    final Employee existentEmployee = repository.findEmployeeByNameAndLastName(employee.getName(),
        employee.getLastName());

    if (existentEmployee != null) {
      throw new BadRequestException("Employee already exists with id: " + employee.getId());
    }

    final Employee savedEmployee = repository.save(employee);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId())
        .toUri();

    return ResponseEntity.created(location).body(savedEmployee);
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
      throw new EntityNotFoundException("Employee not found with id: " + id);
    }
  }

  @PatchMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {

    Optional<Employee> existentEmployee = repository.findById(id);
    if (!existentEmployee.isPresent()) {
      throw new EntityNotFoundException("Employee not found with id: " + id);
    }

    if (updatedEmployee.getChargeability() != null) {
      existentEmployee.get().setChargeability(updatedEmployee.getChargeability());
    }

    if (updatedEmployee.getIsCoordinator() != null) {
      existentEmployee.get().setIsCoordinator(updatedEmployee.getIsCoordinator());
    }

    if (updatedEmployee.getProfile() != null) {
      existentEmployee.get().setProfile(updatedEmployee.getProfile());
    }

    Employee savedEmployee = repository.save(existentEmployee.get());

    return ResponseEntity.ok().body(savedEmployee);
  }
}
