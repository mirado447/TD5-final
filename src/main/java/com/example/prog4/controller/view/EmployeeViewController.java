package com.example.prog4.controller.view;

import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.model.Employee;
import com.example.prog4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeViewController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @GetMapping("/list")
    public String listEmployee() {
        return "employees";
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("employee", Employee.builder().build());
        return "employee_creation";
    }

    @GetMapping("/edit/{eId}")
    public String editEmployee(@PathVariable String eId, Model model) {
        Employee toEdit = employeeMapper.toView(employeeService.getOne(eId));
        model.addAttribute("employee", toEdit);
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
