package com.example.helpful_hangul.service;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.entity.SoundChangeRule;
import com.example.helpful_hangul.repository.SoundChangeRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoundChangeRuleService {

    private final SoundChangeRuleRepository soundChangeRuleRepository;
    private final JamoService jamoService;

    public SoundChangeRuleService(SoundChangeRuleRepository soundChangeRuleRepository, JamoService jamoService) {
        this.soundChangeRuleRepository = soundChangeRuleRepository;
        this.jamoService = jamoService;
    }

    public List<SoundChangeRule> getAllRules() {
        return soundChangeRuleRepository.findAll();
    }

    public SoundChangeRule getRuleById(Long id) {
        return soundChangeRuleRepository.findById(id)
                .orElseThrow(() -> new SoundChangeRuleNotFoundException(id));
    }

    public Optional<SoundChangeRule> resolveRule(Long triggerJamoId, Long followingJamoId) {
        Jamo triggerJamo = jamoService.getJamoById(triggerJamoId);
        Jamo followingJamo = jamoService.getJamoById(followingJamoId);
        return soundChangeRuleRepository.findByTriggerJamoAndFollowingJamo(triggerJamo, followingJamo);
    }

    public List<SoundChangeRule> findByTriggerJamo(Long triggerJamoId) {
        Jamo triggerJamo = jamoService.getJamoById(triggerJamoId);
        return soundChangeRuleRepository.findByTriggerJamo(triggerJamo);
    }
}
