package com.example.helpful_hangul.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JamoNotFoundException extends RuntimeException {

    public JamoNotFoundException(Long id) {
        super("Jamo not found with id: " + id);
    }
}