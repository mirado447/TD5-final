package com.example.prog4.model;

import com.example.prog4.repository.entity.Phone;
import com.example.prog4.repository.entity.TaxIdentity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@ToString
public class CompanyConf {
    private String id;
    private String stringLogo;
    private MultipartFile logo;
    private String email;
    private String address;
    private String name;
    private String description;
    private String slogan;
    private TaxIdentity taxIdentity;
    private List<Phone> phones;
}
