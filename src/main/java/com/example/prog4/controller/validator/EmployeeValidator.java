package com.example.prog4.controller.validator;

import com.example.prog4.model.Employee;
import com.example.prog4.model.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeValidator {
    public void validate(Employee employee){
        StringBuilder error = new StringBuilder();

        if(employee.getBirthDate() == null){
            error.append("Birthdate is mandatory. ");
        } else if(employee.getBirthDate().isAfter(LocalDate.now())){
            error.append("Birthdate could not be after today. ");
        }
        if(employee.getLastName().isBlank() || employee.getLastName().isEmpty()){
            error.append("Last name is mandatory. ");
        }
        if(employee.getFirstName().isBlank() || employee.getFirstName().isEmpty()){
            error.append("First name is mandatory. ");
        }
        if(!error.isEmpty()){
            throw new BadRequestException(error.toString());
        }
    }
}
