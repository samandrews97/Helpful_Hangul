package com.example.helpful_hangul.controller;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.entity.SoundChangeRule;
import com.example.helpful_hangul.service.JamoService;
import com.example.helpful_hangul.service.SoundChangeRuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jamo")
public class JamoController {

    private final JamoService jamoService;
    private final SoundChangeRuleService soundChangeRuleService;

    public JamoController(JamoService jamoService, SoundChangeRuleService soundChangeRuleService) {
        this.jamoService = jamoService;
        this.soundChangeRuleService = soundChangeRuleService;
    }

    @GetMapping
    public List<Jamo> getAllJamo() {
        return jamoService.getAllJamo();
    }

    @GetMapping("/{id}")
    public Jamo getJamoById(@PathVariable Long id) {
        return jamoService.getJamoById(id);
    }

    @GetMapping("/{id}/sound-change-rules")
    public List<SoundChangeRule> getSoundChangeRulesForJamo(@PathVariable Long id) {
        jamoService.getJamoById(id);
        return soundChangeRuleService.findByTriggerJamo(id);
    }
}