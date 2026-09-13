package com.example.helpful_hangul.service;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.repository.JamoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JamoServiceTest {

    @Mock
    private JamoRepository jamoRepository;

    @InjectMocks
    private JamoService jamoService;

    @Test
    void getAllJamo_returnsWhateverTheRepositoryReturns() {
        Jamo giyeok = new Jamo();
        giyeok.setCharacter("ㄱ");
        when(jamoRepository.findAll()).thenReturn(List.of(giyeok));

        List<Jamo> result = jamoService.getAllJamo();

        assertThat(result).containsExactly(giyeok);
    }

    @Test
    void getJamoById_returnsJamo_whenFound() {
        Jamo giyeok = new Jamo();
        giyeok.setCharacter("ㄱ");
        when(jamoRepository.findById(1L)).thenReturn(Optional.of(giyeok));

        Jamo result = jamoService.getJamoById(1L);

        assertThat(result).isEqualTo(giyeok);
    }

    @Test
    void getJamoById_throwsJamoNotFoundException_whenMissing() {
        when(jamoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> jamoService.getJamoById(999L))
                .isInstanceOf(JamoNotFoundException.class);
    }
}