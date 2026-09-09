package com.example.helpful_hangul.controller;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.service.JamoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jamo")
public class JamoController {

    private final JamoService jamoService;

    public JamoController(JamoService jamoService) {
        this.jamoService = jamoService;
    }

    @GetMapping
    public List<Jamo> getAllJamo() {
        return jamoService.getAllJamo();
    }

    @GetMapping("/{id}")
    public Jamo getJamoById(@PathVariable Long id) {
        return jamoService.getJamoById(id);
    }
}