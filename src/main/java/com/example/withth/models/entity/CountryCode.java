package com.example.withth.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class CountryCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true)
    private String content;
    @OneToMany(mappedBy = "countryCode", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Collection<Phone> phone;
}
