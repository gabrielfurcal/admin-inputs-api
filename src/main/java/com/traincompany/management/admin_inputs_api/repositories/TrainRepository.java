package com.traincompany.management.admin_inputs_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traincompany.management.admin_inputs_api.models.Train;

public interface TrainRepository extends JpaRepository<Train, Integer> {
    
}
