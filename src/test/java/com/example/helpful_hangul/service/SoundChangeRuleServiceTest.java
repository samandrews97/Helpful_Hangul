package com.example.helpful_hangul.service;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.entity.SoundChangeRule;
import com.example.helpful_hangul.repository.SoundChangeRuleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SoundChangeRuleServiceTest {

    @Mock
    private SoundChangeRuleRepository soundChangeRuleRepository;

    @Mock
    private JamoService jamoService;

    @InjectMocks
    private SoundChangeRuleService soundChangeRuleService;

    @Test
    void resolveRule_returnsTheRule_whenOneConnectsTheJamo() {
        Jamo triggerJamo = new Jamo();
        Jamo followingJamo = new Jamo();
        SoundChangeRule rule = new SoundChangeRule();

        when(jamoService.getJamoById(1L)).thenReturn(triggerJamo);
        when(jamoService.getJamoById(2L)).thenReturn(followingJamo);
        when(soundChangeRuleRepository.findByTriggerJamoAndFollowingJamo(triggerJamo, followingJamo))
                .thenReturn(Optional.of(rule));

        Optional<SoundChangeRule> result = soundChangeRuleService.resolveRule(1L, 2L);

        assertThat(result).contains(rule);
    }

    @Test
    void resolveRule_returnsEmpty_whenNoRuleConnectsTheJamo() {
        Jamo triggerJamo = new Jamo();
        Jamo followingJamo = new Jamo();

        when(jamoService.getJamoById(1L)).thenReturn(triggerJamo);
        when(jamoService.getJamoById(2L)).thenReturn(followingJamo);
        when(soundChangeRuleRepository.findByTriggerJamoAndFollowingJamo(triggerJamo, followingJamo))
                .thenReturn(Optional.empty());

        Optional<SoundChangeRule> result = soundChangeRuleService.resolveRule(1L, 2L);

        assertThat(result).isEmpty();
    }

    @Test
    void findByTriggerJamo_throwsJamoNotFoundException_whenJamoIdIsInvalid() {
        when(jamoService.getJamoById(999L)).thenThrow(new JamoNotFoundException(999L));

        assertThatThrownBy(() -> soundChangeRuleService.findByTriggerJamo(999L))
                .isInstanceOf(JamoNotFoundException.class);
    }
}