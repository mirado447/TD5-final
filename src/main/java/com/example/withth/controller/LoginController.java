package com.example.withth.controller;

import com.example.withth.controller.request.LoginDetails;
import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.repository.EmployeeConnectorRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {
    private final EmployeeConnectorRepository repository;

    public LoginController(EmployeeConnectorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("AuthUsername");
        session.removeAttribute("AuthPassword");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDetails loginDetails, HttpSession session) {
        List<Employee> allByNameAndPasswordEquals = repository.findAllByPasswordAndName(loginDetails.getPassword(), loginDetails.getUsername());
        if (allByNameAndPasswordEquals.isEmpty()){
            return "redirect:/login";
        }
        Employee employee = allByNameAndPasswordEquals.get(0);
        session.setAttribute("AuthUsername", employee.getFirstName());
        session.setAttribute("AuthPassword", employee.getPassword());
        return "redirect:/";
    }
}
