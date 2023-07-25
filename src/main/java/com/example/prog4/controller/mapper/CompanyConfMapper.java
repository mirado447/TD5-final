package com.example.prog4.controller.mapper;

import com.example.prog4.model.CompanyConf;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class CompanyConfMapper {

    public CompanyConf toView(com.example.prog4.repository.entity.CompanyConf companyConf) {
        return CompanyConf.builder()
                .id(companyConf.getId())
                .phones(companyConf.getPhones())
                .name(companyConf.getName())
                .stringLogo(companyConf.getLogo())
                .email(companyConf.getEmail())
                .description(companyConf.getDescription())
                .taxIdentity(companyConf.getTaxIdentity())
                .slogan(companyConf.getSlogan())
                .build();
    }
}
