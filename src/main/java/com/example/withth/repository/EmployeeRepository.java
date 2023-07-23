package com.example.withth.repository;

import com.example.withth.models.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
            select e from Employee e
            where upper(e.name) like upper(concat('%', ?1, '%')) or upper(e.function) like upper(?2)""")
    List<Employee> filterByNameOrFunction(String name, String function);
}