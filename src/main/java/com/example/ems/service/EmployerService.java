package com.example.ems.service;

import com.example.ems.model.Employer;
import com.example.ems.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployerService {
    @Autowired
    private EmployerRepository employerRepository;

    @Cacheable("employers")
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Cacheable(value = "employers", key = "#id")
    public Employer getEmployerById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
    }

    @CacheEvict(value = "employers", allEntries = true)
    public Employer saveEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @CacheEvict(value = "employers", key = "#id")
    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }
}