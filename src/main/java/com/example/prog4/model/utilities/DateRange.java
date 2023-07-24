package com.example.prog4.model.utilities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DateRange {
    private LocalDate begin;
    private LocalDate end;
}
