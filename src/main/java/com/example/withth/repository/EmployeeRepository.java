package com.example.withth.repository;

import com.example.withth.models.entity.Employee;
import com.example.withth.models.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
            select e from Employee e
            where ((upper(e.name) like upper(concat('%', ?1, '%'))) or e.name=null) and
             (upper(e.function) like upper(?2) or e.function=null ) and upper(e.sex) like ?3""")
    List<Employee> filterByNameOrFunction(String name, String function, String sex);
}