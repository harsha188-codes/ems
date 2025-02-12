package com.example.ems.service;

import com.example.ems.model.Skill;
import com.example.ems.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Cacheable("skills")
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Cacheable(value = "skills", key = "#id")
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    @CacheEvict(value = "skills", allEntries = true)
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @CacheEvict(value = "skills", key = "#id")
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}