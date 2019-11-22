package com.cdnator.coordinator.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import com.cdnator.coordinator.entities.Employee;
import com.cdnator.coordinator.exception.BadRequestException;
import com.cdnator.coordinator.exception.EntityNotFoundException;
import com.cdnator.coordinator.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired
  private EmployeeRepository repository;

  @PostMapping("/employees")
  public ResponseEntity<Employee> insertEmployee(@Valid @RequestBody Employee employee) {

    logger.debug((" --> Entro en el método insertEmployee"));

    // TODO: Al dar de alta un empleado, hay que verificar que no existe ya un
    // coordinador para el equipo seleccionado

    employee.setName(employee.getName().toUpperCase());
    employee.setLastName(employee.getLastName().toUpperCase());

    final Employee existentEmployee = repository.findEmployeeByNameAndLastName(employee.getName(),
        employee.getLastName());

    if (existentEmployee != null) {
      logger.error("ERROR: Method insertEmployee(). Employee already exists with id %s", employee.getId());
      throw new BadRequestException("Employee already exists with id: " + employee.getId());
    }

    final Employee savedEmployee = repository.save(employee);

    final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId())
        .toUri();

    logger.debug((" <-- Salgo del método insertEmployee"));

    return ResponseEntity.created(location).body(savedEmployee);
  }

  @GetMapping("/employees")
  public List<Employee> listEmployees() {

    logger.debug((" --> Entro en el método listEmployees"));

    final List<Employee> listOfEmployees = repository.findAll();

    logger.debug((" <-- Salgo del método listEmployees"));

    return listOfEmployees;
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployee(@PathVariable String id) {

    logger.debug((" --> Entro en el método getEmployee"));

    final Optional<Employee> employee = repository.findById(id);

    if (employee.isPresent()) {
      logger.debug((" <-- Salgo del método getEmployee"));
      return ResponseEntity.ok().body(employee.get());
    } else {
      logger.error("ERROR: method getEmployee(). Employee not found with id", id);
      throw new EntityNotFoundException("Employee not found with id: " + id);
    }
  }

  @PatchMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {

    logger.debug((" --> Entro en el método updateEmployee"));

    final Optional<Employee> existentEmployee = repository.findById(id);
    if (!existentEmployee.isPresent()) {
      logger.error("ERROR: method updateEmployee(). Employee not found with id %s", id);
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

    final Employee savedEmployee = repository.save(existentEmployee.get());

    logger.debug((" <-- Salgo del método updateEmployee"));

    return ResponseEntity.ok().body(savedEmployee);
  }
}
