package com.example.prog4.model;

import com.example.prog4.model.exception.BadRequestException;
import lombok.Getter;

public record PerPage(@Getter Integer perPage) {
    private static final int DEFAULT_PER_PAGE = 10;
    private static final int MAX_PER_PAGE = 500;

    public PerPage(Integer perPage) {
        if (perPage == null) {
            this.perPage = DEFAULT_PER_PAGE;
        } else if (perPage > MAX_PER_PAGE) {
            throw new BadRequestException("Page size must be < " + MAX_PER_PAGE);
        } else {
            this.perPage = perPage;
        }
    }
}
