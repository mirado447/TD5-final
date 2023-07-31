package com.example.withth.service;

import com.example.withth.controller.request.EmployeeFilter;
import com.example.withth.models.entity.Employee;
import com.example.withth.models.entity.Sex;
import com.example.withth.repository.EmployeeRepository;
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
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    @Getter
    @Setter
    private EmployeeFilter lastFilterUsed = new EmployeeFilter();

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public void save(Employee employee){
        repository.save(employee);
    }

    public Employee findById(Long id){
        Optional<Employee> byId = repository.findById(id);
        return byId.orElseGet(Employee::new);
    }

    public List<Employee> filter(String name, String function, Sex sex, String orderBy, Date entryDate, String direction){
        String sexQuery = (sex != null) ? sex.toString():null;
        lastFilterUsed.setName(name);
        lastFilterUsed.setFunction(function);
        lastFilterUsed.setSex(sexQuery);
        lastFilterUsed.setOrderBy(orderBy);
        lastFilterUsed.setDirection(direction);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);



        return repository.filterByNameOrFunction(name, function, sex, entryDate,sort);
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
                        employee.getMatriculate(), employee.getName(), employee.getBirthDate(),
                        employee.getPrivateMail(), employee.getChildrens(), employee.getEntryDate(),
                        employee.getCin(), employee.getAddress(), employee.getPhoneNumbers(),
                        employee.getCnaps(), employee.getFunction(),employee.getProfessionalCategory(),
                        employee.getSex());
            }
        }
    }

}
