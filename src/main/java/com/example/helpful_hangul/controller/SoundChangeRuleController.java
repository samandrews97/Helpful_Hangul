package com.example.helpful_hangul.controller;

import com.example.helpful_hangul.entity.SoundChangeRule;
import com.example.helpful_hangul.service.SoundChangeRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sound-change-rules")
public class SoundChangeRuleController {

    private final SoundChangeRuleService soundChangeRuleService;

    public SoundChangeRuleController(SoundChangeRuleService soundChangeRuleService) {
        this.soundChangeRuleService = soundChangeRuleService;
    }

    @GetMapping
    public List<SoundChangeRule> getAllRules() {
        return soundChangeRuleService.getAllRules();
    }

    @GetMapping("/{id}")
    public SoundChangeRule getRuleById(@PathVariable Long id) {
        return soundChangeRuleService.getRuleById(id);
    }

    @GetMapping("/resolve")
    public ResponseEntity<SoundChangeRule> resolveRule(@RequestParam Long triggerJamoId,
                                                         @RequestParam Long followingJamoId) {
        return soundChangeRuleService.resolveRule(triggerJamoId, followingJamoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}