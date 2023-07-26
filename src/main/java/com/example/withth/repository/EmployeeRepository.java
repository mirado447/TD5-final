package com.example.withth.repository;

import com.example.withth.models.entity.Employee;
import com.example.withth.models.entity.Sex;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
            select e from Employee e
            where
             (upper(e.name) ilike concat('%', :name, '%') or :name is null) and
             (upper(e.function) like concat('%',:function,'%') or :function is null) and
              (upper(e.sex) like :sex or :sex is null )
              """)
    List<Employee> filterByNameOrFunction(@Param("name") String name, @Param("function") String function, @Param("sex") String sex, Sort sort);
}