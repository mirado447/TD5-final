package com.example.withth.repository;

import com.example.withth.models.entity.Employee;
import com.example.withth.models.entity.Sex;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByPasswordAndName(String password, String name);

    @Query("""
            select e from Employee e
            where
            (:name is null or e.name ilike concat('%', :name, '%'))
            and (:function is null or e.function ilike concat('%', :function, '%'))
            and (:sex is null or e.sex = :sex)
            and (cast(:entryDate as date) is null or e.entryDate = cast(:entryDate as date))
            """)
    List<Employee> filterByNameOrFunction(@Param("name") @Nullable String name, @Param("function") @Nullable String function,
                                   @Param("sex") @Nullable Sex sex, @Param("entryDate") @Nullable Date entryDate,
                                   Sort sort);


}