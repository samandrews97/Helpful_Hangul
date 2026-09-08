package com.example.helpful_hangul.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sound_change_rule")
public class SoundChangeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The jongseong (batchim) that triggers the change
    @ManyToOne
    @JoinColumn(name = "trigger_jamo_id", nullable = false)
    private Jamo triggerJamo;

    // The choseong of the following syllable that the trigger reacts with
    @ManyToOne
    @JoinColumn(name = "following_jamo_id", nullable = false)
    private Jamo followingJamo;

    // What the jongseong becomes due to the choseong that follows it
    @ManyToOne
    @JoinColumn(name = "resulting_jamo_id", nullable = false)
    private Jamo resultingJamo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SoundChangeType soundChangeType;

    public Long getId() {
        return id;
    }

    public Jamo getTriggerJamo() {
        return triggerJamo;
    }

    public Jamo getFollowingJamo() {
        return followingJamo;
    }

    public Jamo getResultingJamo() {
        return resultingJamo;
    }

    public SoundChangeType getSoundChangeType() {
        return soundChangeType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTriggerJamo(Jamo triggerJamo) {
        this.triggerJamo = triggerJamo;
    }

    public void setFollowingJamo(Jamo followingJamo) {
        this.followingJamo = followingJamo;
    }

    public void setResultingJamo(Jamo resultingJamo) {
        this.resultingJamo = resultingJamo;
    }

    public void setSoundChangeType(SoundChangeType soundChangeType) {
        this.soundChangeType = soundChangeType;
    }

    public enum SoundChangeType {
        BATCHIM_NEUTRALISATION,
        LIAISON,
        NASALISATION,
        RIEUL_ASSIMILATION,
        PALATALISATION,
        TENSING,
        ASPIRATION,
        HIEUH_DROPPING,
        CONSONANT_CLUSTER_SIMPLIFICATION,
        INTRUSIVE_NIEUN,
        INITIAL_SOUND
    }

}