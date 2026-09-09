package com.example.helpful_hangul.repository;

import com.example.helpful_hangul.entity.Jamo;
import com.example.helpful_hangul.entity.SoundChangeRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoundChangeRuleRepository extends JpaRepository<SoundChangeRule, Long> {

    Optional<SoundChangeRule> findByTriggerJamoAndFollowingJamo(Jamo triggerJamo, Jamo followingJamo);
}