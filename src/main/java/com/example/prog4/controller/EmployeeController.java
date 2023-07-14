package com.example.prog4.controller;


import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.controller.validator.EmployeeValidator;
import com.example.prog4.model.Employee;
import com.example.prog4.model.RestEmployee;
import com.example.prog4.model.message.ErrorMessage;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService service;
    private EmployeeMapper mapper;
    private EmployeeValidator validator;

    @GetMapping("/")
    public String index(Model model) {
        List<Employee> employees = service.getAll();
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        model.addAttribute("employee", RestEmployee.builder().build());
        return "createEmployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") RestEmployee restEmployee, HttpSession session) {
        try {
            validator.validate(restEmployee);
        }
        catch (Exception e){
            session.setAttribute("errorMessage", e.getMessage());
            return "redirect:/errors?code=400";
        }
        Employee employee = mapper.toDomain(restEmployee);
        service.saveOne(employee);
        return "redirect:/";
    }

    @GetMapping("/errors")
    public String error(@RequestParam("code") Integer code, Model model, HttpSession session){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code(code)
                .message((String) session.getAttribute("errorMessage"))
                .build();
        model.addAttribute("error", errorMessage);
        return "errors";
    }


    @GetMapping("/employee/{employeeId}")
    public String getEmployee(@PathVariable(name = "employeeId") String employeeId, Model model) {
        Employee employee = service.getOne(employeeId);
        model.addAttribute("employee", employee);
        return "employee";
    }

}