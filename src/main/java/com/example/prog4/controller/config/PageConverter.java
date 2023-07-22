package com.example.prog4.controller.config;

import com.example.prog4.model.Page;
import org.springframework.core.convert.converter.Converter;

public class PageConverter implements Converter<String, Page> {
    @Override
    public Page convert(String source) {
        if(source == null){
            return new Page(null);
        }
        return new Page(Integer.valueOf(source));
    }
}
