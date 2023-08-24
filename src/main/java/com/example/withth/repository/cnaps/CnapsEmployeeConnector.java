package com.example.withth.repository.cnaps;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.repository.EmployeeConnectorRepository;
import com.example.withth.repository.employeeManagement.jpa.LocalEmployeeRepository;
import com.example.withth.repository.mapper.CnapsMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j

/*
 * To identify if a cnaps is associated with an employee we compare the employee
 * id with the end_to_end_id
 * */
public class CnapsEmployeeConnector implements EmployeeConnectorRepository {
    private final CnapsEmployeeRepository cnapsJpaEmployeeRepository;
    private final LocalEmployeeRepository localEmployeeJpaRepository;

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
        return this.mapToLocalEmployees(localEmployees, allCnapsEmployee);
    }

    @Override
    public void save(Employee employee) {
        throw new RuntimeException("Cnaps connector is read only!");
    }


    /**
     * Replace the cnaps number of each local employees to the cnaps number from cnaps db
     * <p>
     * To retrieve the cnaps number of an employee I use the employee id and the end_to_end_id.
     */
    public List<Employee> mapToLocalEmployees(List<Employee> localEmployees, List<com.example.withth.models.cnaps.Employee> allCnapsEmployee) {
        return localEmployees.stream()
                .map(employee -> allCnapsEmployee.stream()
                        .filter(employee1 -> employee1.getEndToEndId().equals(employee.getId()))
                        .findFirst()
                        .map(cnapsEmployee -> {
                            employee.setCnaps(cnapsEmployee.getCnaps());
                            return employee;
                        })
                        .orElseGet(() -> {
                            employee.setCnaps(null);
                            return employee;
                        })
                )
                .toList();
    }
}
