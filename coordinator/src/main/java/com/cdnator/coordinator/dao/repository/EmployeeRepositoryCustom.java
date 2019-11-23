package com.cdnator.coordinator.dao.repository;

import com.cdnator.coordinator.dao.entity.Employee;

public interface EmployeeRepositoryCustom {

    public Employee findEmployeeByNameAndLastName(String name, String lastName);
}