package com.example.prog4.controller.mapper;

import com.example.prog4.model.Email;
import com.example.prog4.model.Employee;
import com.example.prog4.model.ViewEmployee;
import com.example.prog4.model.exception.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Component
public class EmployeeMapper {
    public com.example.prog4.repository.entity.Employee toDomain(Employee employee) {
        com.example.prog4.repository.entity.Employee domainEmployee = com.example.prog4.repository.entity.Employee.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())

                .address(employee.getAddress())
                .childNumber(employee.getChildNumber())
                .cin(employee.getCin())
                .cnaps(employee.getCnaps())
                .positions(employee.getPositions())
                .registrationNumber(employee.getRegistrationNumber())
                .csp(employee.getCsp())
                .sex(employee.getSex())
                .phone(employee.getPhone())
                .professionalEmail(employee.getEmail().getProfessional())
                .personalEmail(employee.getEmail().getPersonal())

                .birthDate(employee.getBirthDate())
                .departureDate(employee.getDepartureDate())
                .entranceDate(employee.getEntranceDate())

                .build();
        try {
            MultipartFile imageFile = employee.getImage();
            if (imageFile != null && !imageFile.isEmpty()) {
                byte[] imageBytes = imageFile.getBytes();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                domainEmployee.setImage(base64Image);
            }
            return domainEmployee;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public ViewEmployee toView(com.example.prog4.repository.entity.Employee employee) {
        Email email = Email.builder()
                .personal(employee.getPersonalEmail())
                .professional(employee.getProfessionalEmail())
                .build();
        return ViewEmployee.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())

                .address(employee.getAddress())
                .childNumber(employee.getChildNumber())
                .cin(employee.getCin())
                .cnaps(employee.getCnaps())
                .positions(employee.getPositions())
                .registrationNumber(employee.getRegistrationNumber())
                .csp(employee.getCsp())
                .sex(employee.getSex())
                .phone(employee.getPhone())
                .email(email)

                .birthDate(employee.getBirthDate())
                .departureDate(employee.getDepartureDate())
                .entranceDate(employee.getEntranceDate())

                .image(employee.getImage())
                .build();
    }
}
