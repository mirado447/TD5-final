package com.example.withth.repository;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.models.employeeManagement.entity.Sex;
import com.example.withth.repository.cnaps.jpa.CnapsEmployeeRepository;
import com.example.withth.repository.employeeManagement.LocalEmployeeRepository;
import com.example.withth.repository.mapper.CnapsMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class CnapsEmployeeConnector implements EmployeeConnectorRepository{
    private final CnapsEmployeeRepository cnapsJpaEmployeeRepository;
    private final LocalEmployeeRepository localEmployeeJpaRepository;
    private final CnapsMapper mapper;

    @Override
    public Employee findById(Long id) {
        Optional<com.example.withth.models.cnaps.Employee> cnapsEmployee = cnapsJpaEmployeeRepository.findByEndToEndId(id);
        String cnaps = cnapsEmployee.map(com.example.withth.models.cnaps.Employee::getCnaps).orElse(null);

        Employee localEmployee = localEmployeeJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found on local db"));
        localEmployee.setCnaps(cnaps);

        if (cnapsEmployee.isEmpty()) {
            log.info("No cnaps account found for: " + localEmployee);
        }
        return localEmployee;
    }

    @Override
    public List<Employee> findAll() {
        List<com.example.withth.models.cnaps.Employee> allCnapsEmployee = cnapsJpaEmployeeRepository.findAll();
        List<Employee> localEmployees = localEmployeeJpaRepository.findAll();
        return mapper.mapToLocalEmployees(localEmployees, allCnapsEmployee);
    }

    @Override
    public void save(Employee employee) {
        localEmployeeJpaRepository.save(employee);
    }

    @Override
    public List<Employee> filterByNameOrFunction(String name, String function, Sex sex, Date entryDateStart, Date entryDateEnd, Date departureDateStart, Date departureDateEnd, Sort sort) {
        List<com.example.withth.models.cnaps.Employee> allCnapsEmployee = cnapsJpaEmployeeRepository.findAll();
        List<Employee> filteredEmployees = localEmployeeJpaRepository.filterByNameOrFunction(
                name, function, sex, entryDateStart, entryDateEnd, departureDateStart, departureDateEnd, sort
        );

        return mapper.mapToLocalEmployees(filteredEmployees, allCnapsEmployee);
    }

    @Override
    public List<Employee> findAllByPasswordAndName(String password, String username) {
        return localEmployeeJpaRepository.findAllByPasswordAndName(password, username);
    }
}
