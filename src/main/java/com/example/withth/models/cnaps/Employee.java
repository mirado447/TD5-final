package com.example.withth.models.cnaps;

import com.example.withth.models.employeeManagement.entity.Phone;
import com.example.withth.models.employeeManagement.entity.Sex;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String matriculate;
    private String name;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String profilePicture;
    private byte[] content;
    private long size;
    private String address;
    private String privateMail;
    private String publicMail;
    //    number, date et lieu de deliverance,
    private String cin;
    private String function;
    private Integer childrens;
    @Temporal(TemporalType.DATE)
    private Date entryDate = new Date();
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    private String professionalCategory;
    private String cnaps;
    private String password;

    public String getMatriculate() {
        return "EMP" + this.id;
    }
}

