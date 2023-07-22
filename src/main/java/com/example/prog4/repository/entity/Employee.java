package com.example.prog4.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "\"employee\"")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private String  cin;
    private String  cnaps;
    private String  address;
    private String  lastName;
    private String  firstName;
    private Integer childNumber;
    private String  personalEmail;
    private String  professionalEmail;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Enumerated(EnumType.STRING)
    private Csp csp;

    private LocalDate birthDate;
    private LocalDate entranceDate;
    private LocalDate departureDate;

    private String registrationNumber;
    @ManyToMany
    @JoinTable(name = "have_position")
    private List<Position> positions;
    @Lob
    private String image;

    enum Sex {
        H, F
    }

    enum Csp {
        AGRICULTURAL_WORKERS,
        CRAFTSMEN_AND_ARTISANS,
        TRADERS_AND_MERCHANTS,
        CIVIL_SERVANTS_AND_PROFESSIONALS,
        UNSKILLED_LABORERS
    }
}