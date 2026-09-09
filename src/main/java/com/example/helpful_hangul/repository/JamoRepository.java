package com.example.helpful_hangul.repository;

import com.example.helpful_hangul.entity.Jamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JamoRepository extends JpaRepository<Jamo, Long> {
}