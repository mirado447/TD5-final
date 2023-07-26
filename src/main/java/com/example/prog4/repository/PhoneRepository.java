package com.example.prog4.repository;

import com.example.prog4.repository.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {
    @Query(value = "select * from \"phone\" p where p.value = :value", nativeQuery = true)
    Optional<Phone> findOneByValue(@Param("value") String value);

    @Query(value = "select * from \"phone\" p where p.employee_id IS NULL", nativeQuery = true)
    List<Phone> findAllPhoneAvailable();
}
