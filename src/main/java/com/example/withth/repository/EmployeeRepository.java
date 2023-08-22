package com.example.withth.repository;


import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.models.employeeManagement.entity.Sex;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository {
   Employee findById(Long id);

    List<Employee> findAll();

    void save(Employee employee);

    List<Employee> filterByNameOrFunction(
            String name, String function, Sex sex,
            Date entryDateStart, Date entryDateEnd, Date departureDateStart,
            Date departureDateEnd, Sort sort
    );

    List<Employee> findAllByPasswordAndName(String password, String username);
}