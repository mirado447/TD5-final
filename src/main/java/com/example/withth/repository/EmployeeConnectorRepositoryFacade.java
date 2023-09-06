package com.example.withth.repository;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.repository.cnaps.CnapsEmployeeConnector;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@AllArgsConstructor
@Primary
@Slf4j
public class EmployeeConnectorRepositoryFacade implements EmployeeConnectorRepository {
    private final CnapsEmployeeConnector cnapsEmployeeConnector;

    private EmployeeConnectorRepository getEmployeeConnectorRepository() {
        return this.cnapsEmployeeConnector;
    }

    @Override
    public Employee findById(Long id) {
        return getEmployeeConnectorRepository().findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return getEmployeeConnectorRepository().findAll();
    }

    @Override
    public void save(Employee employee) {
        getEmployeeConnectorRepository().save(employee);
    }
}
