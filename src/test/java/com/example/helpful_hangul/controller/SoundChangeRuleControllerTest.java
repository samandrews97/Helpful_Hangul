package com.example.helpful_hangul.controller;

import com.example.helpful_hangul.entity.SoundChangeRule;
import com.example.helpful_hangul.service.SoundChangeRuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SoundChangeRuleController.class)
class SoundChangeRuleControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private SoundChangeRuleService soundChangeRuleService;

    @Test
    void resolveRule_returns200AndRuleJson_whenRuleFound() throws Exception {
        SoundChangeRule rule = new SoundChangeRule();
        rule.setId(1L);

        when(soundChangeRuleService.resolveRule(1L, 2L)).thenReturn(Optional.of(rule));

        mockMvc.perform(get("/api/sound-change-rules/resolve?triggerJamoId=1&followingJamoId=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));


    }

    @Test
    void resolveRule_returns204_whenRuleNotFound() throws Exception {
        when(soundChangeRuleService.resolveRule(1L, 2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/sound-change-rules/resolve?triggerJamoId=1&followingJamoId=2"))
                .andExpect(status().isNoContent());

    }

    @Test
    void getRuleById_returns200AndRuleJson_whenIdFound() throws Exception {
        SoundChangeRule rule = new SoundChangeRule();
        rule.setId(1L);
        when(soundChangeRuleService.getRuleById(1L)).thenReturn(rule);

        mockMvc.perform(get("/api/sound-change-rules/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }

}
