package com.example.withth.controller;

import com.example.withth.model.Employee;
import com.example.withth.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @ModelAttribute("employeeList")
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }

    @RequestMapping("/")
    public String getEmployee() {
        return "index";
    }

    @GetMapping("/employee-details")
    public ModelAndView getEmployeeDetails(@RequestParam(name = "employeeId") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee-details");
        Employee employee = service.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Employee employee, @ModelAttribute("file") MultipartFile file) throws IOException {
        employee.setProfilePicture(file.getOriginalFilename());
        employee.setContent(file.getBytes());
        employee.setSize(file.getSize());
        service.save(employee);
        return "redirect:/";
    }


    @GetMapping("/image")
    public void showImage(@Param("id") Long id, HttpServletResponse response, Employee employee) throws IOException {
        employee = service.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        response.getOutputStream().write(employee.getContent());
        response.getOutputStream().close();
    }

    @GetMapping("/employee-form")
    public ModelAndView addEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("employee-form");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

//    @GetMapping(value = "/edit-employee-form")
//    public ModelAndView editEmployeeForm(@RequestParam(name = "employeeId") Integer id) {
//        ModelAndView modelAndView = new ModelAndView("employee-form");
//        modelAndView.addObject("employee", new Employee(id, "boo", null));
//        return modelAndView;
//    }
}
