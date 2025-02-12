package com.example.ems.service;

import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Cacheable("employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getEmployeesByEmployer(Long employerId) {
        return employeeRepository.findByEmployerId(employerId);
    }

    @CacheEvict(value = "employees", allEntries = true)
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employees", key = "#id")
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}