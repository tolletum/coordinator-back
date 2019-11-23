package com.cdnator.coordinator.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.cdnator.coordinator.dao.EmployeeDao;
import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dto.EmployeeDTO;
import com.cdnator.coordinator.mapper.MapperDTO;

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
  private EmployeeDao dao;

  @Autowired
  private MapperDTO mapper;

  @PostMapping("/employees")
  public ResponseEntity<EmployeeDTO> insertEmployee(@Valid @RequestBody EmployeeDTO employee) {

    // TODO: Al dar de alta un empleado, hay que verificar que no existe ya un
    // coordinador para el equipo seleccionado

    employee.setName(employee.getName().toUpperCase());
    employee.setLastName(employee.getLastName().toUpperCase());

    final Employee savedEmployee = dao.insertEmployee(mapper.employeeDTOToEmployee(employee));

    final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedEmployee.getId()).toUri();

    return ResponseEntity.created(location).body(mapper.employeeToEmployeeDTO(savedEmployee));
  }

  @GetMapping("/employees")
  public List<EmployeeDTO> listEmployees() {

    final List<Employee> listOfEmployees = dao.listEmployees();

    List<EmployeeDTO> listOut = mapper.listEmployeeToEmployeeDTO(listOfEmployees);

    return listOut;
  }

  @GetMapping("/employees/{id}")
  public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable String id) {

    final Employee employee = dao.getEmployee(id);

    return ResponseEntity.ok().body(mapper.employeeToEmployeeDTO(employee));
  }

  @PatchMapping("/employees/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO updatedEmployee) {

    final Employee savedEmployee = dao.updateEmployee(id, mapper.employeeDTOToEmployee(updatedEmployee));

    return ResponseEntity.ok().body(mapper.employeeToEmployeeDTO(savedEmployee));
  }
}
