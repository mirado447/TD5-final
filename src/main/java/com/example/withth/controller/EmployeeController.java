package com.example.withth.controller;

import com.example.withth.models.entity.Employee;
import com.example.withth.models.entity.Sex;
import com.example.withth.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @ModelAttribute("employeeList")
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }

    @ModelAttribute("sexList")
    public List<String> getSex(){
        return Arrays.stream(Sex.values()).map(Sex::toString).toList();
    }

    @RequestMapping("/")
    public String getEmployee() {
        return "employee/index";
    }

    @GetMapping("/employee-details")
    @Deprecated
    public ModelAndView getEmployeeDetails(@RequestParam(name = "employeeId") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/employee-details");
        Employee employee = service.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/employee/{employeeId}")
    public ModelAndView getEmployeeDetails2(@PathVariable(name = "employeeId") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/employee-details");
        Employee employee = service.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }
    @PostMapping("/search")
    public String searchByKeyWord(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String function,
                                  @RequestParam(required = false) String sex,
                                  RedirectAttributes redirectAttributes) {
        if (name.equals("")) {
            name = null;
        }
        if (function.equals("")) {
            function = null;
        }
        List<String> sexList = Arrays.stream(Sex.values()).map(Enum::toString).toList();
        if (!sexList.contains(sex)) {
            sex = null;
        }


        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("function", function);
        redirectAttributes.addAttribute("sex", sex);
        return "redirect:/filter";
    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String name,
                         @RequestParam(required = false) String function,
                         @RequestParam(required = false) String sex,
                         Model model) {
        // when running a query like filter?name=&function=k
        // the query params of name will be an empty string
        if (Objects.equals(name, "")) {
            name = null;
        }
        if (Objects.equals(function, "")) {
            function = null;
        }

        Sex sexQuery;
        List<String> sexList = Arrays.stream(Sex.values()).map(Enum::toString).toList();
        if (!sexList.contains(sex) || sex==null) {
            sexQuery = null;
        }else {
            sexQuery = Sex.valueOf(sex);
        }

        List<Employee> listEmployees = service.filter(name, function, sexQuery);
        model.addAttribute("query_name", name);
        model.addAttribute("query_function", function);
        model.addAttribute("query_sex", sexQuery);
        model.addAttribute("employeeList", listEmployees);
        return "employee/index";
    }



    @GetMapping("/employee/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<Employee> employees = service.getEmployees();
        service.exportToCSV(response, employees);
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Employee employee, @ModelAttribute("file") MultipartFile file) throws IOException {
        if (file != null) {
            employee.setProfilePicture(file.getOriginalFilename());
            employee.setContent(file.getBytes());
            employee.setSize(file.getSize());
        }
        service.save(employee);
        return "redirect:/";
    }

    @PostMapping(value = "/edit")
    public String updateEmployee(@ModelAttribute Employee employee, @ModelAttribute("file") MultipartFile file) throws IOException {
        byte[] content;
        if (file == null) {
            Employee oldEmployee = service.findById(employee.getId());
            content = oldEmployee.getContent();
        } else {
            employee.setSize(file.getSize());
            employee.setProfilePicture(file.getOriginalFilename());
            content = file.getBytes();
        }
        employee.setContent(content);
        service.save(employee);
        return "redirect:/";
    }


    @GetMapping("/image")
    public void showImage(@Param("id") Long id, HttpServletResponse response, Employee employee) throws IOException {
        employee = service.findById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        byte[] content = employee.getContent();
        if (content != null) {
            response.getOutputStream().write(employee.getContent());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/employee-form")
    public ModelAndView addEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("employee/employee-form");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editEmployeeForm(@Param("id") Long id) {
        Employee employee = service.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }
}
