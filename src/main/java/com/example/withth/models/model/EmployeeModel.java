package com.example.withth.models.model;

import com.example.withth.models.entity.Phone;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmployeeModel implements Serializable {
    private Long id;
    private String matriculate;
    private String name;
    private String birthDate;
    private Sex sex;
    private String profilePicture;
    private byte[] content;
    private long size;
    private List<Phone> phoneNumbers;
    private String address;
    private String privateMail;
    private String publicMail;
    //    number, date et lieu de deliverance,
    private String cin;
    private String function;
    private Integer childrens;
    private String entryDate;
    private String departureDate;
    private String professionalCategory;
    private String cnaps;

    public enum Sex {H, F}

    public String getMatriculate() {
        return "EMP" + this.id;
    }
}

