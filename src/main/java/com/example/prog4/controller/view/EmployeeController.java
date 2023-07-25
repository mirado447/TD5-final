package com.example.prog4.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping("/list")
    public String listEmployee() {
        return "employees";
    }

    @GetMapping("/create")
    public String createEmployee() {
        return "employee_edition";
    }

    @GetMapping("/edit/{eId}")
    public String editEmployee(@PathVariable String eId) {
        return "employee_edition";
    }

    @GetMapping("/show/{eId}")
    public String showEmployee(@PathVariable String eId) {
        return "employee_show";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
