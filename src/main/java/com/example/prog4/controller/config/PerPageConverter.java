package com.example.prog4.controller.config;

import com.example.prog4.model.PerPage;
import org.springframework.core.convert.converter.Converter;

public class PerPageConverter implements Converter<String, PerPage> {
    @Override
    public PerPage convert(String source) {
        if (source == null){
            return new PerPage(null);
        }
        return new PerPage(Integer.getInteger(source));
    }
}
