package com.example.helpful_hangul.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SoundChangeRuleNotFoundException extends RuntimeException {

    public SoundChangeRuleNotFoundException(Long id) {
        super("SoundChangeRule not found with id: " + id);
    }
}