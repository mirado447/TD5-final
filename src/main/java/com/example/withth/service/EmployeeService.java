package com.example.withth.service;

import com.example.withth.controller.request.EmployeeFilter;
import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.models.employeeManagement.entity.Phone;
import com.example.withth.models.employeeManagement.entity.Sex;
import com.example.withth.repository.EmployeeConnectorRepository;
import com.example.withth.repository.employeeManagement.jpa.LocalEmployeeRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeConnectorRepository repository;
    private final LocalEmployeeRepository localEmployeeRepository;
    @Getter
    @Setter
    private EmployeeFilter lastFilterUsed = new EmployeeFilter();

    public EmployeeService(EmployeeConnectorRepository repository, LocalEmployeeRepository localEmployeeRepository) {
        this.repository = repository;
        this.localEmployeeRepository = localEmployeeRepository;
    }

    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public void save(Employee employee){
        List<Phone> phoneLinkedToEmployee = employee.getPhones();
        for (int i = 0; i < phoneLinkedToEmployee.size(); i++) {
            Phone phone = phoneLinkedToEmployee.get(i);
            phone.setEmployee(employee);
        }
        employee.setPhones(phoneLinkedToEmployee);
        repository.save(employee);
    }

    public Employee findById(Long id){
        return repository.findById(id);
    }

    public List<Employee> filter(String name, String function, Sex sex, String orderBy, Date entryDateStart, String direction, Date entryDateEnd, Date departureDateStart, Date departureDateEnd){
        String sexQuery = (sex != null) ? sex.toString():null;
        lastFilterUsed.setFirstName(name);
        lastFilterUsed.setFunction(function);
        lastFilterUsed.setSex(sexQuery);
        lastFilterUsed.setOrderBy(orderBy);
        lastFilterUsed.setDirection(direction);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);



        return localEmployeeRepository.filterByNameOrFunction(name, function, sex, entryDateStart,entryDateEnd,departureDateStart, departureDateEnd,sort);
    }

    public void exportToCSV(HttpServletResponse response, List<Employee> employees) throws IOException {
        String csvFileName = "employees.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + csvFileName + "\"");
        //create a csv writer
        String[] headers = {"Matricule", "Nom", "DateDeNaissance",
                "E-mail", "ChildrenInCharges", "EntryDate",
                "CIN", "Address", "PhoneNumbers",
                "CNAPS", "Position", "ProfessionalCategory",
                "Sex"};
        try (CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT.withHeader(headers))) {
            //write each employee to the csv file
            for (Employee employee : employees) {
                csvPrinter.printRecord(
                        employee.getMatriculate(), employee.getFirstName(), employee.getBirthDate(),
                        employee.getPrivateMail(), employee.getChildrens(), employee.getEntryDate(),
                        employee.getCin(), employee.getAddress(), employee.getPhones(),
                        employee.getCnaps(), employee.getFunction(),employee.getProfessionalCategory(),
                        employee.getSex());
            }
        }
    }

    public List<Employee> findAllByPasswordAndName(String password, String username) {
        return localEmployeeRepository.findAllByPasswordAndName(password, username);
    }
}
