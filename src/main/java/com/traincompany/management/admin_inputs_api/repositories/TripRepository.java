package com.traincompany.management.admin_inputs_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traincompany.management.admin_inputs_api.models.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    
}
