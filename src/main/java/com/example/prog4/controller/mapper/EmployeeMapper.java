package com.example.prog4.controller.mapper;

import com.example.prog4.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Component
public class EmployeeMapper {
    public com.example.prog4.repository.entity.Employee toDomain(Employee employee){
        com.example.prog4.repository.entity.Employee domainEmployee = com.example.prog4.repository.entity.Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .build();
        try {
            MultipartFile imageFile = employee.getImage();
            if (imageFile != null && !imageFile.isEmpty()) {
                // Get the image file content as a byte array
                byte[] imageBytes = imageFile.getBytes();

                // Convert the byte array to a Base64 string
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                domainEmployee.setImage(base64Image);
            }
            return domainEmployee;
        }
        catch (Exception e){
            throw new RuntimeException("Bad Request");
        }
    }
}
