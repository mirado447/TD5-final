package com.example.withth.controller;

import com.example.withth.models.entity.Company;
import com.example.withth.repository.CompanyRepository;
import com.example.withth.service.CompanyService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Base64;

@AllArgsConstructor
@Controller
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @GetMapping("/company/{id}")
    public ModelAndView companyDetails(@PathVariable(name = "id") Long id) {
        Company company = companyService.getCompanyDetails(id);
        company = getCompany(company);
        ModelAndView modelAndView = new ModelAndView("company/details");
        modelAndView.addObject("company", company);
        return modelAndView;
    }

    private Company getCompany(Company company) {
        if (company == null) {
            company = new Company();
            company.setId(1L);
            companyService.saveCompanyDetails(company);
        }
        return company;
    }

    @GetMapping("/company/{id}/edit")
    public ModelAndView editCompanyInfos(ModelAndView modelAndView, @PathVariable(name = "id") Long id) {
        Company company = companyService.getCompanyDetails(id);
        company = getCompany(company);
        modelAndView.setViewName("company/edit-form");
        modelAndView.addObject("company", company);
        return modelAndView;
    }
    @PostMapping("/company/save")
    public String saveCompany(@ModelAttribute("company") Company company, @ModelAttribute("file") MultipartFile file) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        String base64Image = encoder.encodeToString(file.getBytes());
        company.setLogo(base64Image);
        company.setLogoB(file.getBytes());
        companyService.save(company);
        return "redirect:/";
    }

    @GetMapping("/company/image")
    public void showImage(@Param("id") Long id, HttpServletResponse response,  Company company) throws IOException {
        company = companyService.getCompanyDetails(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        byte[] content = company.getLogoB();
        if (content != null) {
            response.getOutputStream().write(content);
            response.getOutputStream().close();
        }
    }
}
