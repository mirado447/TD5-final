package com.example.prog4.controller.mapper;

import com.example.prog4.model.Employee;
import com.example.prog4.model.exception.BadRequestException;
import com.example.prog4.repository.PhoneRepository;
import com.example.prog4.repository.PositionRepository;
import com.example.prog4.repository.entity.Phone;
import com.example.prog4.repository.entity.Position;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
public class EmployeeMapper {
    private PhoneRepository phoneRepository;
    private PositionRepository positionRepository;

    public com.example.prog4.repository.entity.Employee toDomain(Employee employee) {
        try {
            List<Position> positions = positionRepository.saveAll(employee.getPositions());
            List<Phone> phones = phoneRepository.saveAll(employee.getPhones());

            com.example.prog4.repository.entity.Employee domainEmployee = com.example.prog4.repository.entity.Employee.builder()
                    .id(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .address(employee.getAddress())
                    .cin(employee.getCin())
                    .cnaps(employee.getCnaps())
                    .registrationNumber(employee.getRegistrationNumber())
                    .childrenNumber(employee.getChildrenNumber())
                    // enums
                    .csp(employee.getCsp())
                    .sex(employee.getSex())
                    // emails
                    .professionalEmail(employee.getProfessionalEmail())
                    .personalEmail(employee.getPersonalEmail())
                    // dates
                    .birthDate(employee.getBirthDate())
                    .departureDate(employee.getDepartureDate())
                    .entranceDate(employee.getEntranceDate())
                    // lists
                    .phones(phones)
                    .positions(positions)
                    .build();
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

    public Employee toView(com.example.prog4.repository.entity.Employee employee) {
        return Employee.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .address(employee.getAddress())
                .cin(employee.getCin())
                .cnaps(employee.getCnaps())
                .registrationNumber(employee.getRegistrationNumber())
                .childrenNumber(employee.getChildrenNumber())
                // enums
                .csp(employee.getCsp())
                .sex(employee.getSex())
                .stringImage(employee.getImage())
                // emails
                .professionalEmail(employee.getProfessionalEmail())
                .personalEmail(employee.getPersonalEmail())
                // dates
                .birthDate(employee.getBirthDate())
                .departureDate(employee.getDepartureDate())
                .entranceDate(employee.getEntranceDate())
                // lists
                .phones(employee.getPhones())
                .positions(employee.getPositions())
                .build();
    }
}
