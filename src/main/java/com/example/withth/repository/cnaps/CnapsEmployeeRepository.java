package com.example.withth.repository.cnaps;

import com.example.withth.models.cnaps.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CnapsEmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEndToEndId(Long id);
}