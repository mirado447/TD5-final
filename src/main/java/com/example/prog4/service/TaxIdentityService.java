package com.example.prog4.service;

import com.example.prog4.repository.TaxIdentityRepository;
import com.example.prog4.repository.entity.TaxIdentity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaxIdentityService {
    private TaxIdentityRepository repository;

    public List<TaxIdentity> getAll(){
        return repository.findAll();
    }

    public TaxIdentity saveOne(TaxIdentity position){
        return repository.save(position);
    }
}
