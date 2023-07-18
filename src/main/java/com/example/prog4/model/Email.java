package com.example.prog4.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class Email implements Serializable {
    private String professional;
    private String personal;
}
