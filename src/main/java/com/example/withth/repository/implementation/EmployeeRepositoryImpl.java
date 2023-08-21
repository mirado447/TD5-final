package com.example.withth.repository.implementation;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.models.employeeManagement.entity.Sex;
import com.example.withth.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final CnapsEmployeeRepository cnapsJpaEmployeeRepository;
    private final LocalEmployeeRepository localEmployeeJpaRepository;

    @Override
    @Transactional
    public Employee findById(Long id) {
        com.example.withth.models.cnaps.Employee cnapsEmployee = cnapsJpaEmployeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found on cnaps db"));
        Employee localEmployee = localEmployeeJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found on local db"));
        localEmployee.setCnaps(cnapsEmployee.getCnaps());

        return localEmployee;
    }

    @Override
    public List<Employee> findAll() {
        List<com.example.withth.models.cnaps.Employee> allCnapsEmployee = cnapsJpaEmployeeRepository.findAll();
        List<Employee> localEmployees = localEmployeeJpaRepository.findAll();
        return mapToLocalEmployees(localEmployees, allCnapsEmployee);
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

        return mapToLocalEmployees(filteredEmployees, allCnapsEmployee);
    }

    /**
     * Replace the cnaps number of each local employees to the cnaps number from cnaps db
     * <p>
     * Fixme: To retrieve the cnaps number of an employee i use the employee id.
     */
    private static List<Employee> mapToLocalEmployees(List<Employee> localEmployees, List<com.example.withth.models.cnaps.Employee> allCnapsEmployee) {
        return localEmployees.stream()
                .map(employee -> {
                    List<com.example.withth.models.cnaps.Employee> list = allCnapsEmployee.stream()
                            // cnaps number identification
                            .filter(employee1 -> employee1.getId().equals(employee.getId())).toList();

                    if (list.isEmpty())
                        employee.setCnaps(null);
                    else
                        employee.setCnaps(list.get(0).getCnaps());
                    return employee;
                }).toList();
    }
}
