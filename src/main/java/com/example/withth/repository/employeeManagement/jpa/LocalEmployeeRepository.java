package com.example.withth.repository.employeeManagement.jpa;

import com.example.withth.models.employeeManagement.entity.Employee;
import com.example.withth.models.employeeManagement.entity.Sex;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

public interface LocalEmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByPasswordAndName(String password, String name);

    @Query("""
                select e from Employee e
                where
                (:name is null or e.firstName ilike concat('%', :name, '%'))
                and (:function is null or e.function ilike concat('%', :function, '%'))
                and (:sex is null or e.sex = :sex)
                and (
                    (cast(:entryDateStart as date) is null or e.entryDate >= cast(:entryDateStart as date))
                    and (cast(:entryDateEnd as date) is null or e.entryDate <= cast(:entryDateEnd as date))
                )
                and (
                    (cast(:departureDateStart as date) is null or e.departureDate >= cast(:departureDateStart as date))
                    and (cast(:departureDateEnd as date) is null or e.departureDate <= cast(:departureDateEnd as date))
                )
            """)
    List<Employee> filterByNameOrFunction(@Param("name") @Nullable String name, @Param("function") @Nullable String function,
                                          @Param("sex") @Nullable Sex sex,
                                          @Param("entryDateStart") Date entryDateStart, @Param("entryDateEnd") Date entryDateEnd,
                                          @Param("departureDateStart") Date departureDateStart, @Param("departureDateEnd") Date departureDateEnd,
                                          Sort sort);


}