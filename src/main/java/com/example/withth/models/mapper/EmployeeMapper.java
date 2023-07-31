package com.example.withth.models.mapper;

import com.example.withth.models.entity.Employee;
import com.example.withth.models.model.EmployeeModel;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeModel toModel(Employee entity){

        return EmployeeModel.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .publicMail(entity.getPublicMail())
                .cin(entity.getCin())
                .address(entity.getAddress())
                .cnaps(entity.getCnaps())
                .birthDate(entity.getBirthDate().toString())
                .childrens(entity.getChildrens())
                .departureDate(entity.getDepartureDate().toString())
                .entryDate(entity.getEntryDate().toString())
                .sex(EmployeeModel.Sex.valueOf(String.valueOf(entity.getSex())))
                .matriculate(entity.getMatriculate())
                .phoneNumbers(entity.getPhones())
                .function(entity.getFunction())
                .size(entity.getSize())
                .name(entity.getName())
                .build();
    }
}
