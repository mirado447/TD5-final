package com.example.prog4.controller;

import com.example.prog4.controller.mapper.CompanyConfMapper;
import com.example.prog4.model.CompanyConf;
import com.example.prog4.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@AllArgsConstructor
@Controller
public class GlobalControllerAdvice {
    private CompanyService companyService;
    private CompanyConfMapper companyConfMapper;

    @ModelAttribute(name = "enterprise")
    public CompanyConf globalEnterprise() {
        String enterpriseId = "376e0ac6-2a32-417f-a2fd-95403df6c778";
        System.out.println(enterpriseId);
        return companyConfMapper.toView(companyService.getOne(enterpriseId));
    }
}
