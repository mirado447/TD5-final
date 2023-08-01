package com.example.withth.service;

import com.example.withth.models.entity.CountryCode;
import com.example.withth.repository.CountryCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryCodeService {
    private final CountryCodeRepository repository;
    public List<CountryCode> findAll(){
        return repository.findAll();
    }
}
