package com.example.prog4.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Employee implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private MultipartFile image;
    private String cin;
    private String address;
    private String cnaps;
    private Integer childNumber;
    private Email email;
    private NameDescription socioeconomicGroup;
    private List<NameDescription> position;

    //dates 
    private LocalDate birthDate;
    private LocalDate entranceDate;
    private LocalDate departureDate;

    private String registrationNumber;
}
