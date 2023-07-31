package com.example.prog4.service;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.model.enums.EmployeeSortField;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.model.utilities.DateRange;
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

    public List<Employee> getAll(EmployeeFilter filter) {
        Sort sort = Sort.by(filter.getOrderBy().toString());

        if (filter.getOrderDirection().isAscending()) {
            sort.ascending();
        } else {
            sort.descending();
        }

        Pageable pageable = PageRequest.of(filter.getIntPage(), filter.getIntPerPage(), sort);
        System.out.println(filter);
        return employeeManagerDao.findByCriteria(
                filter.getLastName(),
                filter.getFirstName(),
                filter.getCountryCode(),
                filter.getSex(),
                filter.getPosition(),
                filter.getEntrance(),
                filter.getDeparture(),
                pageable
        );
    }

    public void saveOne(Employee employee) {
        repository.save(employee);
    }
}
