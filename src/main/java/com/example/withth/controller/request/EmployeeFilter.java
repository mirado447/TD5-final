package com.example.withth.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeFilter {
    private String name;
    private String function;
    private String sex;
    private Date entryDate;
    private Date departureDate;
    private String orderBy="name";
    private String direction="asc";
}
