package com.example.withth.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Enterprise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private String slogan;
    private byte[] logo;
    private long logoSize;
    // TODO an employee can have many phone number
    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phoneNumbers;
    private String address;
    private String fiscalId;
}

