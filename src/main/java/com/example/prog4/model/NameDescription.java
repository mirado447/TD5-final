package com.example.prog4.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class NameDescription implements Serializable {
    private String name;
    private String description;
}
