package com.example.withth.models.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private Long id;
    private String matriculate;
    private String name;
    private LocalDate birthDate;
    private String profilePicture;
    private byte[] content;
    private long size;
    // TODO an employee can have many phone number
    private String phoneNumber;
    private String address;
    private String privateMail;
    private String publicMail;
    //    number, date et lieu de deliverance,
    private String cin;
    private String function;
    private Integer childrens = 0;

    private enum sex {H, F}

    public String getMatriculate() {
        return "EMP" + this.id;
    }
}
