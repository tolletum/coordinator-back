package com.cdnator.coordinator.dao;

import java.util.List;
import java.util.Optional;

import com.cdnator.coordinator.dao.entity.Employee;
import com.cdnator.coordinator.dao.repository.EmployeeRepository;
import com.cdnator.coordinator.exception.BadRequestException;
import com.cdnator.coordinator.exception.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

  @Autowired
  private EmployeeRepository repository;

  public Employee insertEmployee(Employee employee) {

    final Employee existentEmployee = repository.findEmployeeByNameAndLastName(employee.getName(),
        employee.getLastName());

    if (existentEmployee != null) {
      logger.error("ERROR: Method insertEmployee(). Employee already exists with id %s", employee.getId());
      throw new BadRequestException("Employee already exists with id: " + employee.getId());
    }

    final Employee savedEmployee = repository.save(employee);

    return savedEmployee;
  }

  public List<Employee> listEmployees() {

    final List<Employee> listOfEmployees = repository.findAll();

    return listOfEmployees;
  }

  public Employee getEmployee(String id) {

    final Optional<Employee> employee = repository.findById(id);

    if (employee.isPresent()) {
      return employee.get();
    } else {
      logger.error("ERROR: method getEmployee(). Employee not found with id", id);
      throw new EntityNotFoundException("Employee not found with id: " + id);
    }
  }

  public Employee updateEmployee(String id, Employee updatedEmployee) {

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

    if (updatedEmployee.getTeam() != null) {
      existentEmployee.get().setTeam(updatedEmployee.getTeam());
    }

    final Employee savedEmployee = repository.save(existentEmployee.get());

    return savedEmployee;
  }

  public void deleteEmployee(String id) {

    repository.deleteById(id);
  }

}
