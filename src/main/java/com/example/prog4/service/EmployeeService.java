package com.example.prog4.service;

import com.example.prog4.model.enums.EmployeeSortField;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.model.utilities.DateRange;
import com.example.prog4.model.utilities.Page;
import com.example.prog4.model.utilities.PerPage;
import com.example.prog4.repository.EmployeeRepository;
import com.example.prog4.repository.dao.EmployeeManagerDao;
import com.example.prog4.repository.entity.Employee;
import com.example.prog4.repository.entity.enums.Sex;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;
    private EmployeeManagerDao employeeManagerDao;


    public Employee getOne(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found id=" + id));
    }

    public List<Employee> getAll(
            String lastName,
            String firstName,
            Sex sex,
            String position,
            Page page,
            PerPage perPage,
            EmployeeSortField orderBy,
            Direction orderDirection,
            DateRange entranceDateRange,
            DateRange departureDateRange
    ) {
        Sort sort = Sort.by(orderBy.toString());

        if (orderDirection.isAscending()) {
            sort.ascending();
        } else {
            sort.descending();
        }

        Pageable pageable = PageRequest.of(page.getPage(), perPage.getPerPage(), sort);
        return employeeManagerDao.findByCriteria(
                lastName,
                firstName,
                sex,
                position,
                entranceDateRange,
                departureDateRange,
                pageable
        );
    }

    public Employee saveOne(Employee employee) {
        return repository.save(employee);
    }
}
