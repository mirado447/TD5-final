package com.example.prog4.controller;


import com.example.prog4.model.Employee;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class WebController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        model.addAttribute(Employee.builder().build());
        return "createEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveStudent(@ModelAttribute("employee") Employee employee, HttpSession session) {
        if(session.getAttribute("employees") == null){
            session.setAttribute("employees", new ArrayList<>());
        }
        List<Employee> employees = new ArrayList<>((List<Employee>) session.getAttribute("employees"));

        employee.setId(UUID.randomUUID().toString());

        employees.add(employee);

        session.setAttribute("employees", employees);

        return "redirect:/";
    }
}