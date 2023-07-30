package com.example.withth.repository;

import com.example.withth.models.entity.Employee;
import com.example.withth.models.entity.Sex;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByPasswordAndName(String password, String name);
    @Query("""
            select e from Employee e
            where
             (upper(e.name) ilike concat('%', :name, '%') or :name is null) and
             (upper(e.function) like concat('%',:function,'%') or :function is null) and
              (upper(e.sex) like :sex or :sex is null ) and
              (:entryDate is null  or e.entryDate=:entryDate)
              """)
    List<Employee> filterByNameOrFunction(@Param("name") String name, @Param("function") String function,
                                          @Param("sex") String sex, @Param("entryDate") Date entryDate,
                                          Sort sort);

    @Query("""
            select e from Employee e
            where
            (:name is null or e.name ilike concat('%', :name, '%'))
            and (:function is null or e.function ilike concat('%', :function, '%'))
            and (:sex is null or e.sex = :sex)
            and (cast(:entryDate as date) is null or e.entryDate = cast(:entryDate as date))
            order by e.name, e.function, e.entryDate
            """)
    List<Employee> filterByNameOrFunction(@Param("name") @Nullable String name, @Param("function") @Nullable String function,
                                          @Param("sex") @Nullable Sex sex, @Param("entryDate") @Nullable Date entryDate,
                                          Sort sort);


//    @Query("""
//            select e from Employee e
//            where
//             (e.name is null or upper(e.name) ilike concat('%',?1,'%')) and
//             (e.function is null or upper(e.function) ilike concat('%',?2,'%')) and
//             (e.sex is null or e.sex = ?3) and
//             (e.entryDate is null or e.entryDate = ?4)
//             """)
//    List<Employee> filterByNameOrFunction(String name, String function, Sex sex, Date entryDate, Sort sort);
}