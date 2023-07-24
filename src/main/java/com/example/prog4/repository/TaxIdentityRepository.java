package com.example.prog4.repository;

import com.example.prog4.repository.entity.TaxIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxIdentityRepository extends JpaRepository<TaxIdentity, String> {
}
