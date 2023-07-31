package com.example.prog4.controller;

import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.controller.mapper.SexMapper;
import com.example.prog4.controller.validator.EmployeeValidator;
import com.example.prog4.model.Employee;
import com.example.prog4.model.enums.EmployeeSortField;
import com.example.prog4.model.utilities.DateRange;
import com.example.prog4.model.utilities.Page;
import com.example.prog4.model.utilities.PerPage;
import com.example.prog4.service.CSVUtils;
import com.example.prog4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/server/employee")
public class EmployeeController {
    private EmployeeMapper employeeMapper;
    private EmployeeValidator employeeValidator;
    private EmployeeService employeeService;
    private SexMapper sexMapper;

    @GetMapping("/list/csv")
    public ResponseEntity<byte[]> getCsv(HttpSession session) {
        String firstName = (String) session.getAttribute("firstNameFilter");
        String lastName = (String) session.getAttribute("lastNameFilter");
        String sex = (String) session.getAttribute("sexFilter");
        String position = (String) session.getAttribute("positionFilter");
        LocalDate beginEntrance = (LocalDate) session.getAttribute("beginEntranceFilter");
        LocalDate endingEntrance = (LocalDate) session.getAttribute("endingEntranceFilter");
        LocalDate beginDeparture = (LocalDate) session.getAttribute("beginDepartureFilter");
        LocalDate endingDeparture = (LocalDate) session.getAttribute("endingDepartureFilter");
        EmployeeSortField orderBy = (EmployeeSortField) session.getAttribute("orderByFilter");
        Sort.Direction orderDirection = (Sort.Direction) session.getAttribute("orderDirectionFilter");
        Page page = (Page) session.getAttribute("pageFilter");
        PerPage perPage = (PerPage) session.getAttribute("perPageFilter");

        List<Employee> data = employeeService.getAll(
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
        ).stream().map(employeeMapper::toView).toList();

        String csv = CSVUtils.convertToCSV(data);
        byte[] bytes = csv.getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "employees.csv");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @GetMapping("/list/filters/clear")
    public String clearFilters(HttpSession session) {
        session.removeAttribute("firstNameFilter");
        session.removeAttribute("lastNameFilter");
        session.removeAttribute("sexFilter");
        session.removeAttribute("positionFilter");
        session.removeAttribute("beginEntranceFilter");
        session.removeAttribute("endingEntranceFilter");
        session.removeAttribute("beginDepartureFilter");
        session.removeAttribute("endingDepartureFilter");
        session.removeAttribute("orderByFilter");
        session.removeAttribute("orderDirectionFilter");
        session.removeAttribute("pageFilter");
        session.removeAttribute("perPageFilter");
        return "redirect:/employee/list";
    }

    @GetMapping("/list/filters/set")
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
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction orderDirection,
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
        ).stream().map(employeeMapper::toView).toList());
        return "redirect:employees";
    }

    @PostMapping("/createOrUpdate")
    public String saveOne(@ModelAttribute Employee employee) {
        employeeValidator.validate(employee);
        com.example.prog4.repository.entity.Employee domain = employeeMapper.toDomain(employee);
        employeeService.saveOne(domain);
        return "redirect:/employee/list";
    }
}
