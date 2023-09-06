package com.example.withth.service;

import com.example.withth.models.employeeManagement.entity.CountryCode;
import com.example.withth.repository.employeeManagement.jpa.CountryCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryCodeService {
    private final CountryCodeRepository repository;
    public List<CountryCode> findAll(){
        return repository.findAll();
    }

    public CountryCode findByContent(CountryCode countryCode){
        String content = countryCode.getContent();
        Optional<CountryCode> byContentEquals = repository.findByContentEquals(content);
        return byContentEquals.orElse(null);
    }
}
