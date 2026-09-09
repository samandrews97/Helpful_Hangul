package com.example.helpful_hangul.seed;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.entity.JamoType;
import com.example.helpful_hangul.entity.SoundChangeRule;
import com.example.helpful_hangul.repository.JamoRepository;
import com.example.helpful_hangul.repository.SoundChangeRuleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final JamoRepository jamoRepository;
    private final SoundChangeRuleRepository soundChangeRuleRepository;

    public DataSeeder(JamoRepository jamoRepository, SoundChangeRuleRepository soundChangeRuleRepository) {
        this.jamoRepository = jamoRepository;
        this.soundChangeRuleRepository = soundChangeRuleRepository;
    }

    @Override
    public void run(String... args) {
        // TODO(human): seed a handful of real jamo (via jamoRepository) and at
        // least one SoundChangeRule connecting them (via soundChangeRuleRepository).
        if (jamoRepository.count() > 0) {
            return;
        }

        // Add data for characters
        Jamo giyeok = newConsonant("ㄱ", "Giyeok", "g/k", 0x3131,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");
        Jamo nieun = newConsonant("ㄴ", "Nieun", "n", 0x3134, Jamo.Manner.PLAIN,
                true, true, false, "to be added",
                "to be added");
        Jamo ieung = newConsonant("ㅇ", "Ieung", "ng", 0x3147, Jamo.Manner.PLAIN,
                true, true, false, "to be added",
                "to be added");
        Jamo a = newVowel("ㅏ", "A", "a", 0x314F, false,
                "to be added");

        jamoRepository.saveAll(List.of(giyeok, nieun, ieung, a));

        // Add data for sound change rules
        SoundChangeRule nasalisation = new SoundChangeRule();
        nasalisation.setTriggerJamo(giyeok);
        nasalisation.setFollowingJamo(nieun);
        nasalisation.setResultingJamo(ieung);
        nasalisation.setSoundChangeType(SoundChangeRule.SoundChangeType.NASALISATION);

        soundChangeRuleRepository.save(nasalisation);

    }

    private Jamo newConsonant(String character, String name, String romanisation, int unicodeValue,
                              Jamo.Manner manner, boolean canBeChoseong, boolean canBeJongseong,
                              boolean isComplex, String choseongAudioUrl, String jongseongAudioUrl) {
        Jamo jamo = new Jamo();
        jamo.setCharacter(character);
        jamo.setName(name);
        jamo.setRomanisation(romanisation);
        jamo.setJamoType(JamoType.CONSONANT);
        jamo.setUnicodeValue(unicodeValue);
        jamo.setManner(manner);
        jamo.setCanBeChoseong(canBeChoseong);
        jamo.setCanBeJungseong(false);
        jamo.setCanBeJongseong(canBeJongseong);
        jamo.setIsComplex(isComplex);
        jamo.setChoseongAudioUrl(choseongAudioUrl);
        jamo.setJongseongAudioUrl(jongseongAudioUrl);
        return jamo;

    }

    private Jamo newVowel(String character, String name, String romanisation, int unicodeValue, boolean isComplex,
                          String jungseongAudioUrl) {
        Jamo jamo = new Jamo();
        jamo.setCharacter(character);
        jamo.setName(name);
        jamo.setRomanisation(romanisation);
        jamo.setJamoType(JamoType.VOWEL);
        jamo.setUnicodeValue(unicodeValue);
        jamo.setCanBeChoseong(false);
        jamo.setCanBeJungseong(true);
        jamo.setCanBeJongseong(false);
        jamo.setIsComplex(isComplex);
        jamo.setJungseongAudioUrl(jungseongAudioUrl);
        return jamo;

    }
}