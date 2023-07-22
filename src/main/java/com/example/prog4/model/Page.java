package com.example.prog4.model;

import com.example.prog4.model.exception.BadRequestException;
import lombok.Getter;

public record Page(@Getter Integer page) {
    public Page(Integer page) {
        if (page == null) {
            this.page = 0;
        } else if (page < 1) {
            throw new BadRequestException("Page cannot be < 1");
        } else {
            this.page = page;
        }
    }
}
