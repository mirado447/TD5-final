package com.example.withth.repository.cnaps;

import com.example.withth.models.cnaps.Employee;
import com.example.withth.models.cnaps.Sex;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CnapsEmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEndToEndId(Long id);
}