package com.example.helpful_hangul.service;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.repository.JamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JamoService {

    private final JamoRepository jamoRepository;

    public JamoService(JamoRepository jamoRepository) {
        this.jamoRepository = jamoRepository;
    }

    public List<Jamo> getAllJamo() {
        return jamoRepository.findAll();
    }

    public Jamo getJamoById(Long id) {
        return jamoRepository.findById(id)
                .orElseThrow(() -> new JamoNotFoundException(id));
    }
}