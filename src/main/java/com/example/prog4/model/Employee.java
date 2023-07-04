package com.example.prog4.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class Employee implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
