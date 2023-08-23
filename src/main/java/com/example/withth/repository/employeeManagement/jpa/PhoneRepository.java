package com.example.withth.repository.employeeManagement.jpa;

import com.example.withth.models.employeeManagement.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByCountryCodeContentAndNumber(String countryCode, String number);
}