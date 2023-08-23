package com.example.withth.repository.implementation;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.models.employeeManagement.entity.Sex;
import com.example.withth.repository.cnaps.CnapsEmployeeConnector;
import com.example.withth.repository.EmployeeConnectorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Override
    public List<Employee> filterByNameOrFunction(
            String name, String function, Sex sex,
            Date entryDateStart, Date entryDateEnd,
            Date departureDateStart, Date departureDateEnd, Sort sort
    ) {
        return getEmployeeConnectorRepository().filterByNameOrFunction(
                name, function, sex,
                entryDateStart, entryDateEnd, departureDateStart,
                departureDateEnd, sort
        );
    }

    @Override
    public List<Employee> findAllByPasswordAndName(String password, String username) {
        return getEmployeeConnectorRepository().findAllByPasswordAndName(password, username);
    }
}
