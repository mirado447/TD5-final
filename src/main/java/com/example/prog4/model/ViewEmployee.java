package com.example.prog4.model;

import com.example.prog4.repository.entity.Position;
import com.example.prog4.repository.entity.enums.Csp;
import com.example.prog4.repository.entity.enums.Sex;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ViewEmployee implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String image;
    private String cin;
    private String address;
    private String cnaps;
    private Integer childNumber;
    private Email email;
    private Csp csp;
    private Sex sex;
    private List<Position> positions;

    //dates 
    private LocalDate birthDate;
    private LocalDate entranceDate;
    private LocalDate departureDate;

    private String registrationNumber;
}
