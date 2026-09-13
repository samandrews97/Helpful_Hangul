package com.example.helpful_hangul.controller;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.service.JamoNotFoundException;
import com.example.helpful_hangul.service.JamoService;
import com.example.helpful_hangul.service.SoundChangeRuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JamoController.class)
class JamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JamoService jamoService;

    @MockitoBean
    private SoundChangeRuleService soundChangeRuleService;

    @Test
    void getJamoById_returns200AndJamoJson_whenFound() throws Exception {
        Jamo giyeok = new Jamo();
        giyeok.setCharacter("ㄱ");
        giyeok.setName("Giyeok");
        when(jamoService.getJamoById(1L)).thenReturn(giyeok);

        mockMvc.perform(get("/api/jamo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.character").value("ㄱ"))
                .andExpect(jsonPath("$.name").value("Giyeok"));
    }

    @Test
    void getJamoById_returns404_whenNotFound() throws Exception {
        when(jamoService.getJamoById(999L)).thenThrow(new JamoNotFoundException(999L));

        mockMvc.perform(get("/api/jamo/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllJamo_returns200AndJsonArray() throws Exception {
        Jamo giyeok = new Jamo();
        giyeok.setCharacter("ㄱ");
        when(jamoService.getAllJamo()).thenReturn(List.of(giyeok));

        mockMvc.perform(get("/api/jamo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].character").value("ㄱ"));
    }
}