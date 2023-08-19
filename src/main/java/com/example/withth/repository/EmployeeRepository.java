package com.example.withth.repository;


import com.example.withth.models.cnaps.Employee;

public interface EmployeeRepository {
    public Employee findById(Long id);
}