package com.example.withth.repository.implementation;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@AllArgsConstructor
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final com.example.withth.repository.cnaps.jpa.CnapsEmployeeRepository cnapsJpaEmployeeRepository;
    private final com.example.withth.repository.employeeManagement.EmployeeRepository localEmployeeRepository;

    @Override
    @Transactional
    public Employee findById(Long id) {
        com.example.withth.models.cnaps.Employee cnapsEmployee = cnapsJpaEmployeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found on cnaps db"));
        Employee localEmployee = localEmployeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found on local db"));
        localEmployee.setCnaps(cnapsEmployee.getCnaps());

        return localEmployee;
    }
}
