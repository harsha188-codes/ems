package com.example.ems.service;

import com.example.ems.model.Project;
import com.example.ems.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Cacheable("projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Cacheable(value = "projects", key = "#id")
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public List<Project> getProjectsByEmployer(Long employerId) {
        return projectRepository.findByEmployerId(employerId);
    }

    @CacheEvict(value = "projects", allEntries = true)
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @CacheEvict(value = "projects", key = "#id")
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}