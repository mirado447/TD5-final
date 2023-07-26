package com.example.withth.controller.request;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeFilter {
    private String name;
    private String function;
    private String sex;
    private LocalDate entryDate;
    private LocalDate departureDate;
    private String orderBy="name";
    private String direction="asc";
}
