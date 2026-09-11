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
        if (jamoRepository.count() > 0) {
            return;
        }

        // Add data for characters
        // Consonants
        Jamo giyeok = newConsonant("ㄱ", "Giyeok", "g/k", 0x3131,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo ssanggiyeok = newConsonant("ㄲ", "Ssanggiyeok", "kk", 0x3132,
                Jamo.Manner.TENSE, true, true, false,
                "to be added", "to be added");

        Jamo nieun = newConsonant("ㄴ", "Nieun", "n", 0x3134,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo digeut = newConsonant("ㄷ", "Digeut", "d/t", 0x3137,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo ssangdigeut = newConsonant("ㄸ", "Ssangdigeut", "tt", 0x3138,
                Jamo.Manner.TENSE, true, false, false,
                "to be added", "to be added");

        Jamo rieul = newConsonant("ㄹ", "Rieul", "r/l", 0x3139,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo mieum = newConsonant("ㅁ", "Mieum", "m", 0x3141,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo bieup = newConsonant("ㅂ", "Bieup", "b/p", 0x3142,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo ssangbieup = newConsonant("ㅃ", "Ssangbieup", "pp", 0x3143,
                Jamo.Manner.TENSE, true, false, false,
                "to be added", "to be added");

        Jamo siot = newConsonant("ㅅ", "Siot", "s", 0x3145,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo ssangsiot = newConsonant("ㅆ", "Ssangsiot", "ss", 0x3146,
                Jamo.Manner.TENSE, true, true, false,
                "to be added", "to be added");

        Jamo ieung = newConsonant("ㅇ", "Ieung", "ng", 0x3147,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo jieut = newConsonant("ㅈ", "Jieut", "j", 0x3148,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        Jamo ssangjieut = newConsonant("ㅉ", "Ssangjieut", "jj", 0x3149,
                Jamo.Manner.TENSE, true, false, false,
                "to be added", "to be added");

        Jamo chieut = newConsonant("ㅊ", "Chieut", "ch", 0x314A,
                Jamo.Manner.ASPIRATED, true, true, false,
                "to be added", "to be added");

        Jamo kieuk = newConsonant("ㅋ", "Kieuk", "k", 0x314B,
                Jamo.Manner.ASPIRATED, true, true, false,
                "to be added", "to be added");

        Jamo tieut = newConsonant("ㅌ", "Tieut", "t", 0x314C,
                Jamo.Manner.ASPIRATED, true, true, false,
                "to be added", "to be added");

        Jamo pieup = newConsonant("ㅍ", "Pieup", "p", 0x314D,
                Jamo.Manner.ASPIRATED, true, true, false,
                "to be added", "to be added");

        Jamo hieut = newConsonant("ㅎ", "Hieut", "h", 0x314E,
                Jamo.Manner.PLAIN, true, true, false,
                "to be added", "to be added");

        // Vowels
        Jamo a = newVowel("ㅏ", "A", "a", 0x314F, false,
                "to be added");

        Jamo ae = newVowel("ㅐ", "Ae", "ae", 0x3150, true,
                "to be added");

        Jamo ya = newVowel("ㅑ", "Ya", "ya", 0x3151, false,
                "to be added");

        Jamo yae = newVowel("ㅒ", "Yae", "yae", 0x3152, true,
                "to be added");

        Jamo eo = newVowel("ㅓ", "Eo", "eo", 0x3153, false,
                "to be added");

        Jamo e = newVowel("ㅔ", "E", "e", 0x3154, true,
                "to be added");

        Jamo yeo = newVowel("ㅕ", "Yeo", "yeo", 0x3155, false,
                "to be added");

        Jamo ye = newVowel("ㅖ", "Ye", "ye", 0x3156, true,
                "to be added");

        Jamo o = newVowel("ㅗ", "O", "o", 0x3157, false,
                "to be added");

        Jamo wa = newVowel("ㅘ", "Wa", "wa", 0x3158, true,
                "to be added");

        Jamo wae = newVowel("ㅙ", "Wae", "wae", 0x3159, true,
                "to be added");

        Jamo oe = newVowel("ㅚ", "Oe", "oe", 0x315A, true,
                "to be added");

        Jamo yo = newVowel("ㅛ", "Yo", "yo", 0x315B, false,
                "to be added");

        Jamo u = newVowel("ㅜ", "U", "u", 0x315C, false,
                "to be added");

        Jamo wo = newVowel("ㅝ", "Wo", "wo", 0x315D, true,
                "to be added");

        Jamo we = newVowel("ㅞ", "We", "we", 0x315E, true,
                "to be added");

        Jamo wi = newVowel("ㅟ", "Wi", "wi", 0x315F, true,
                "to be added");

        Jamo yu = newVowel("ㅠ", "Yu", "yu", 0x3160, false,
                "to be added");

        Jamo eu = newVowel("ㅡ", "Eu", "eu", 0x3161, false,
                "to be added");

        Jamo ui = newVowel("ㅢ", "Ui", "ui", 0x3162, true,
                "to be added");

        Jamo i = newVowel("ㅣ", "I", "i", 0x3163, false,
                "to be added");

        jamoRepository.saveAll(List.of(giyeok, ssanggiyeok, nieun, digeut, ssangdigeut, rieul, mieum, bieup, ssangbieup,
                siot, ssangsiot, ieung, jieut, ssangjieut, chieut, kieuk, tieut, pieup, hieut, a, ae, ya, yae, eo, e,
                yeo, ye, o, wa, wae, oe, yo, u, wo, we, wi, yu, eu, ui, i));

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