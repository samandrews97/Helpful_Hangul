package com.example.helpful_hangul.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jamo")
public class Jamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Jamo identity
    @Column(nullable = false, unique = true)
    private String character;
    @Column(nullable = false)
    private String name;
    private String romanisation;
    @Enumerated(EnumType.STRING)
    private JamoType jamoType;
    private int unicodeValue;

    // Jamo linguistic properties
    @Enumerated(EnumType.STRING)
    private Manner manner;
    private boolean canBeChoseong;
    private boolean canBeJungseong;
    private boolean canBeJongseong;
    private boolean isComplex;

    // Jamo audio
    private String choseongAudioUrl;
    private String jongseongAudioUrl;

    public Long getId() {
        return id;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getRomanisation() {
        return romanisation;
    }

    public JamoType getJamoType() {
        return jamoType;
    }

    public Manner getManner() {
        return manner;
    }

    public boolean canBeChoseong() {
        return canBeChoseong;
    }

    public boolean canBeJungseong() {
        return canBeJungseong;
    }

    public boolean isCanBeJongseong() {
        return canBeJongseong;
    }

    public boolean isComplex() {
        return isComplex;
    }

    public int getUnicodeValue() {
        return unicodeValue;
    }

    public String getChoseongAudioUrl() {
        return choseongAudioUrl;
    }

    public String getJongseongAudioUrl() {
        return jongseongAudioUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRomanisation(String romanisation) {
        this.romanisation = romanisation;
    }

    public void setJamoType(JamoType jamoType) {
        this.jamoType = jamoType;
    }

    public void setManner(Manner manner) {
        this.manner = manner;
    }

    public void setCanBeChoseong(boolean canBeChoseong) {
        this.canBeChoseong = canBeChoseong;
    }

    public void setCanBeJungseong(boolean canBeJungseong) {
        this.canBeJungseong = canBeJungseong;
    }

    public void setCanBeJongseong(boolean canBeJongseong) {
        this.canBeJongseong = canBeJongseong;
    }

    public void setIsComplex(boolean isComplex) {
        this.isComplex = isComplex;
    }

    public void setUnicodeValue(int unicodeValue) {
        this.unicodeValue = unicodeValue;
    }

    public void setChoseongAudioUrl(String choseongAudioUrl) {
        this.choseongAudioUrl = choseongAudioUrl;
    }

    public void setJongseongAudioUrl(String jongseongAudioUrl) {
        this.jongseongAudioUrl = jongseongAudioUrl;
    }

    public enum Manner{
        PLAIN,
        ASPIRATED,
        TENSE
    }

}

