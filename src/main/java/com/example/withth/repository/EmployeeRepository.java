package com.example.withth.repository;


import com.example.withth.models.employeeManagement.entity.Employee;

public interface EmployeeRepository {
    public Employee findById(Long id);
}