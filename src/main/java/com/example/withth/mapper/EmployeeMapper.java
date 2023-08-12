//package com.example.withth.mapper;
//
//import com.example.withth.models.domain.Employee;
//import com.example.withth.models.dto.EmployeeCreateDto;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@AllArgsConstructor
//@Component
//public class EmployeeMapper {
//    public Employee toDomain(EmployeeCreateDto dto){
//        return Employee.builder()
//                .id(dto.getId())
//                .firstName(dto.getFirstName())
//                .lastName(dto.getLastName())
//                .phones(dto.getPhones())
//                .cin(dto.getCin())
//                .content(dto.getContent())
//                .address(dto.getAddress())
//                .cnaps(dto.getCnaps())
//                .birthDate(dto.getBirthDate())
//                .function(dto.getFunction())
//                .childrens(dto.getChildrens())
//                .password(dto.getPassword())
//                .name(dto.getName())
//                .professionalCategory(dto.getProfessionalCategory())
//                .sex(dto.getSex())
//                .departureDate(dto.getDepartureDate())
//                .entryDate(dto.getEntryDate())
//                .privateMail(dto.getPrivateMail())
//                .size(dto.getSize())
//                .sex(dto.getSex())
//                .build();
//    }
//
//    public EmployeeCreateDto toDto(Employee domain){
//        return EmployeeCreateDto.builder()
//                .id(domain.getId())
//                .firstName(domain.getFirstName())
//                .lastName(domain.getLastName())
//                .cin(domain.getCin())
//                .content(domain.getContent())
//                .address(domain.getAddress())
//                .cnaps(domain.getCnaps())
//                .birthDate(domain.getBirthDate())
//                .function(domain.getFunction())
//                .childrens(domain.getChildrens())
//                .password(domain.getPassword())
//                .name(domain.getName())
//                .professionalCategory(domain.getProfessionalCategory())
//                .sex(domain.getSex())
//                .departureDate(domain.getDepartureDate())
//                .entryDate(domain.getEntryDate())
//                .privateMail(domain.getPrivateMail())
//                .size(domain.getSize())
//                .sex(domain.getSex())
//                .build();
//    }
//}
