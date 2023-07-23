package com.example.withth.service;

import com.example.withth.models.entity.Employee;
import com.example.withth.repository.EmployeeRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
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

    public List<Employee> filter(String name, String function){
        return repository.filterByNameOrFunction(name, function);
    }

    public void exportToCSV(HttpServletResponse response, List<Employee> employees) throws IOException {
        String csvFileName = "employees.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + csvFileName + "\"");

        //create a csv writer
        CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT.withHeader("Matricule", "Nom", "Prenom", "DateNaissance", "Mail"));

        //write each employee to the csv file
        for (Employee employee : employees) {
            csvPrinter.printRecord(employee.getMatriculate(), employee.getName(), employee.getBirthDate(), employee.getPrivateMail());
        }

        csvPrinter.close();
    }

}
