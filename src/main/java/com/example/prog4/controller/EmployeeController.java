package com.example.prog4.controller;


import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.controller.mapper.SexMapper;
import com.example.prog4.model.Employee;
import com.example.prog4.model.enums.EmployeeSortField;
import com.example.prog4.model.utilities.DateRange;
import com.example.prog4.model.utilities.Page;
import com.example.prog4.model.utilities.PerPage;
import com.example.prog4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper mapper;
    private SexMapper sexMapper;

    @GetMapping("/getAll")
    public String getAll(
            @RequestParam(required = false, defaultValue = "") String firstName,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(required = false, defaultValue = "") String sex,
            @RequestParam(required = false, defaultValue = "") String position,
            @RequestParam(required = false) LocalDate beginEntrance,
            @RequestParam(required = false) LocalDate beginDeparture,
            @RequestParam(required = false) LocalDate endingEntrance,
            @RequestParam(required = false) LocalDate endingDeparture,
            @RequestParam(required = false, defaultValue = "lastName") EmployeeSortField orderBy,
            @RequestParam(required = false, defaultValue = "ASC") Direction orderDirection,
            @RequestParam(required = false, defaultValue = "1") Page page,
            @RequestParam(required = false, defaultValue = "10") PerPage perPage,
            Model model
    ) {
        model.addAttribute("employees", employeeService.getAll(
                lastName,
                firstName,
                sexMapper.toDomain(sex),
                position,
                page,
                perPage,
                orderBy,
                orderDirection,
                DateRange.builder().begin(beginEntrance).end(endingEntrance).build(),
                DateRange.builder().begin(beginDeparture).end(endingDeparture).build()
        ).stream().map(mapper::toView).toList());
        return "redirect:employees";
    }

    @PostMapping("/employee/create")
    public String saveOne(@ModelAttribute Employee employee){
        com.example.prog4.repository.entity.Employee domain = mapper.toDomain(employee);
        employeeService.saveOne(domain);
        return "redirect:/employee/list";
    }
}