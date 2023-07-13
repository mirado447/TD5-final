package com.example.withth.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employees {
    private List<Employee> employees = new ArrayList<>();
    public void addEmployee(Employee employee){
        employees.add(employee);
    }
}
