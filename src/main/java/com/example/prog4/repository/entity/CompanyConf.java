package com.example.prog4.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"company_conf\"")
@EqualsAndHashCode
@ToString
public class CompanyConf {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    @Column(name = "image")
    private String logo;
    @Column(name = "professional_email")
    private String email;
    private String address;
    private String name;
    private String description;
    private String slogan;
    @OneToOne
    private TaxIdentity taxIdentity;
    @OneToMany
    private List<Phone> phones;
}
