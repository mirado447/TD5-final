package com.example.prog4.service;

import com.example.prog4.model.exception.BadRequestException;
import com.example.prog4.repository.CompanyConfRepository;
import com.example.prog4.repository.entity.CompanyConf;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {
    private CompanyConfRepository repository;

    public CompanyConf saveOne(CompanyConf position) {
        return repository.save(position);
    }

    public CompanyConf getOne(String eId) {
        return repository.findById(eId).orElseThrow(() -> new BadRequestException("Enterprise with id=" + eId + " is not found"));
    }
}
