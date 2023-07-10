package com.example.prog4.service;

import com.example.prog4.model.Employee;
import com.example.prog4.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;


    public Employee getOne(String id){
        Optional<Employee> employee = repository.findById(id);
        if(employee.isPresent()){
            return  employee.get();
        }
        throw new RuntimeException("Not found id="+id);
    }

    public List<Employee> getAll(){
        return repository.findAll();
    }

    public void saveAll(List<Employee> employees){
        repository.saveAll(employees);
    }
    public void saveOne(Employee employee){
        repository.save(employee);
    }
}
