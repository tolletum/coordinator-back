package com.cdnator.coordinator.dao.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.cdnator.coordinator.dao.entity.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public Employee findEmployeeByNameAndLastName(final String name, final String lastName) {
        final Query query = entityManager.createNativeQuery(
                "SELECT em.* FROM coordinator.employee as em " + "WHERE em.name = ? AND lastname = ?", Employee.class);
        query.setParameter(1, name);
        query.setParameter(2, lastName);

        final List<Employee> employees = (List<Employee>) query.getResultList();
        if (employees != null && !employees.isEmpty()) {
            return employees.get(0);
        } else {
            return null;
        }
    }

}