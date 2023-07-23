package com.example.prog4.controller;


import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.controller.mapper.SexMapper;
import com.example.prog4.controller.validator.EmployeeValidator;
import com.example.prog4.model.Employee;
import com.example.prog4.model.ViewEmployee;
import com.example.prog4.model.enums.EmployeeSortField;
import com.example.prog4.model.utilities.Page;
import com.example.prog4.model.utilities.PerPage;
import com.example.prog4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService service;
    private EmployeeMapper mapper;
    private SexMapper sexMapper;
    private EmployeeValidator validator;

    @GetMapping("/employees")
    public List<ViewEmployee> getAll(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(required = false, defaultValue = "") String sex,
            @RequestParam(required = false, defaultValue = "") String position,
            @RequestParam(required = false, defaultValue = "lastName") EmployeeSortField orderBy,
            @RequestParam(required = false, defaultValue = "ASC") Direction orderDirection,
            @RequestParam(required = false, defaultValue = "1") Page page,
            @RequestParam(required = false, defaultValue = "10") PerPage perPage
    ) {
        return service.getAll(
                lastName,
                firstName,
                sexMapper.toDomain(sex),
                position,
                page,
                perPage,
                orderBy,
                orderDirection
        ).stream().map(mapper::toView).toList();
    }

    @PutMapping("/employee")
    public ViewEmployee saveOne(@RequestBody Employee employee) {
        validator.validate(employee);
        com.example.prog4.repository.entity.Employee domain = mapper.toDomain(employee);
        return mapper.toView(service.saveOne(domain));
    }

    @GetMapping("/employee")
    public ViewEmployee getOne(@RequestParam String eId) {
        return mapper.toView(service.getOne(eId));
    }
}