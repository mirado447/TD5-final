package com.example.withth.repository;


import com.example.withth.models.employeeManagement.entity.Employee;

import java.util.List;

public interface EmployeeConnectorRepository {
    Employee findById(Long id);

    List<Employee> findAll();

    void save(Employee employee);
}