package com.example.withth.repository.employeeManagement;

import com.example.withth.models.employeeManagement.entity.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {
    Optional<CountryCode> findByContentEquals(String content);
}