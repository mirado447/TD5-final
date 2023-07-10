package com.example.prog4.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class RestEmployee implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private MultipartFile image;
}
