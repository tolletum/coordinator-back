package com.cdnator.coordinator.dao.repository;

import com.cdnator.coordinator.dao.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>, EmployeeRepositoryCustom {

}