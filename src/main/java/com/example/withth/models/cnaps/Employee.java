package com.example.withth.models.cnaps;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
    private Long endToEndId;
}

