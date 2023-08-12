//package com.example.withth.models.dto;
//
//import com.example.withth.models.domain.Phone;
//import com.example.withth.models.domain.Sex;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class EmployeeCreateDto {
//    private Long id;
//    private String matriculate;
//    private String name;
//    private String firstName;
//    private String lastName;
//    private LocalDate birthDate;
//    private Sex sex;
//    private String profilePicture;
//    private MultipartFile file;
//    private byte[] content;
//    private long size;
//    private String address;
//    private String privateMail;
//    private String publicMail;
//    //    number, date et lieu de deliverance,
//    private String cin;
//    private String function;
//    private Integer childrens;
//    private Date entryDate = new Date();
//    private Date departureDate;
//    private String professionalCategory;
//    private String cnaps;
//    private String password;
//
//    private List<Phone> phones = new ArrayList<>();
//
//}
