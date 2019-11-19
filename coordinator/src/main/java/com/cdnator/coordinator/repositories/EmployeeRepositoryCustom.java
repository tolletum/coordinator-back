package com.cdnator.coordinator.repositories;

import com.cdnator.coordinator.entities.Employee;

public interface EmployeeRepositoryCustom {

    public Employee findEmployeeByNameAndLastName(String name, String lastName);
}