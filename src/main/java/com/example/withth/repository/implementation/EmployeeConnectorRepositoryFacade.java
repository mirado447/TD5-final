package com.example.withth.repository.implementation;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.models.employeeManagement.entity.Sex;
import com.example.withth.repository.EmployeeConnectorRepository;
import com.example.withth.repository.cnaps.jpa.CnapsEmployeeRepository;
import com.example.withth.repository.employeeManagement.LocalEmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
@Primary
@Slf4j
public class EmployeeConnectorRepositoryFacade implements EmployeeConnectorRepository {

    private EmployeeConnectorRepository getEmployeeConnectorRepository(){
        return null;
    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public List<Employee> filterByNameOrFunction(String name, String function, Sex sex, Date entryDateStart, Date entryDateEnd, Date departureDateStart, Date departureDateEnd, Sort sort) {
        return null;
    }

    @Override
    public List<Employee> findAllByPasswordAndName(String password, String username) {
        return null;
    }
}
