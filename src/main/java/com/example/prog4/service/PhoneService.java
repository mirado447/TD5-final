package com.example.prog4.service;

import com.example.prog4.model.enums.EmployeeSortField;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.model.utilities.Page;
import com.example.prog4.model.utilities.PerPage;
import com.example.prog4.repository.EmployeeRepository;
import com.example.prog4.repository.PositionRepository;
import com.example.prog4.repository.dao.EmployeeManagerDao;
import com.example.prog4.repository.entity.Employee;
import com.example.prog4.repository.entity.Position;
import com.example.prog4.repository.entity.enums.Sex;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PositionService {
    private PositionRepository repository;

    public List<Position> getAll(){
        return repository.findAll();
    }

    public Position saveOne(Position position){
        return repository.save(position);
    }
}
