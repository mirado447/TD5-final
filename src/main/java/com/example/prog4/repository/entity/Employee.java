package com.example.prog4.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"employee\"")
@EqualsAndHashCode
@ToString
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private String firstName;
    private String lastName;
    private String cin;
    private String address;
    private String cnaps;
    private Integer childNumber;

    private LocalDate birthDate;
    private LocalDate entranceDate;
    private LocalDate departureDate;

    private String registrationNumber;

    @OneToOne
    private Email email;
    @ManyToOne
    private SocioeconomicGroup socioeconomicGroup;
    @ManyToMany
    @JoinTable(name = "have_position")
    private List<Position> positions;
    @Lob
    private String image;
}