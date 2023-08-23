package com.example.withth.repository.mapper;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.repository.EmployeeConnectorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CnapsMapper {
    private final EmployeeConnectorRepository employeeRepository;

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

    public Employee toDomainEmployeeEntity(com.example.withth.models.cnaps.Employee cnapsEmployee) {
        return Employee.builder()
                .id(cnapsEmployee.getId())
                .firstName(cnapsEmployee.getFirstName())
                .lastName(cnapsEmployee.getLastName())
                // Fixme
//                .phones(cnapsEmployee.getPhones())
                .cin(cnapsEmployee.getCin())
                .content(cnapsEmployee.getContent())
                .address(cnapsEmployee.getAddress())
                .cnaps(cnapsEmployee.getCnaps())
                .birthDate(cnapsEmployee.getBirthDate())
                .function(cnapsEmployee.getFunction())
                .childrens(cnapsEmployee.getChildrens())
                .password(cnapsEmployee.getPassword())
                .name(cnapsEmployee.getName())
                .professionalCategory(cnapsEmployee.getProfessionalCategory())
                // Fixme
//                .sex(cnapsEmployee.getSex())
                .departureDate(cnapsEmployee.getDepartureDate())
                .entryDate(cnapsEmployee.getEntryDate())
                .privateMail(cnapsEmployee.getPrivateMail())
                .size(cnapsEmployee.getSize())
                .build();
    }
}
