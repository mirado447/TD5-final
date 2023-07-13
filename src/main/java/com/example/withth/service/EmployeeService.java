package com.example.withth.service;

import com.example.withth.model.Employee;
import com.example.withth.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
