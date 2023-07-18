package com.example.prog4.controller;

import com.example.prog4.model.exception.ApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InternalToRestException {
    @ExceptionHandler(value = {ApiException.class})
    String handleBadRequest(ApiException e) {
        String DEFAULT_ERROR_MESSAGE = "Unexpected error.";
        String errorMessage = e.getMessage() == null ? DEFAULT_ERROR_MESSAGE : e.getMessage();
        return "<div style='width:99vw;height:99vh;display:flex;justify-content:center;align-items:center'><div><h1>" + e.getCode() + "</h1><br/><p>" + errorMessage + "</p><div></div>";
    }
}
