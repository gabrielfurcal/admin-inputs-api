package com.traincompany.management.admin_inputs_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traincompany.management.admin_inputs_api.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Optional<Employee> findByUserId(String userId);
}
