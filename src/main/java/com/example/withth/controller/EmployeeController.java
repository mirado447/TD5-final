package com.example.withth.controller;

import com.example.withth.models.entity.Employee;
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
import java.util.Objects;

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
    @Deprecated
    public ModelAndView getEmployeeDetails(@RequestParam(name = "employeeId") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee-details");
        Employee employee = service.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/employee/{employeeId}")
    public ModelAndView getEmployeeDetails2(@PathVariable(name = "employeeId") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee-details");
        Employee employee = service.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String function,
                         Model model) {
        // when running a query like filter?name=&function=k
        // the query params of name will be an empty string
        if (Objects.equals(name, "")) {
            name=null;
        }
        if (Objects.equals(function, "")) {
            function=null;
        }

        List<Employee> listEmployees = service.filter(name, function);
        model.addAttribute("employeeList", listEmployees);
        return "index";
    }
    @GetMapping("/employee/export")
    public void exportToCSV(HttpServletResponse response) throws IOException{
        List<Employee> employees = service.getEmployees();
        service.exportToCSV(response,employees);
    }
    @PostMapping(value = "/save")
    public String save(@ModelAttribute Employee employee, @ModelAttribute("file") MultipartFile file) throws IOException {
        employee.setProfilePicture(file.getOriginalFilename());
        employee.setContent(file.getBytes());
        employee.setSize(file.getSize());
        service.save(employee);
        return "redirect:/";
    }

    @PostMapping(value = "/edit")
    public String updateEmployee(@ModelAttribute Employee employee, @ModelAttribute("file") MultipartFile file) throws IOException {
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

    @GetMapping("/edit")
    public ModelAndView editEmployeeForm(@Param("id") Long id) {
        Employee employee = service.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }
}
