package com.example.withth.models.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nom;
    private String description;
    private String slogan;
    private String adresse;
    private String emailContact;
    @ElementCollection
    private List<String> telephones;
    private String nif;
    private String stat;
    private String rcs;
    @Lob
    private Byte[] logo;
}
