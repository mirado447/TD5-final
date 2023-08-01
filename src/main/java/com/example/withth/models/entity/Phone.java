package com.example.withth.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Phone implements Serializable {
    @Id
    @GeneratedValue()
    private Long id;
    private String number;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}