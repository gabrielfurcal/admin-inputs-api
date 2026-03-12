package com.traincompany.management.admin_inputs_api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.traincompany.management.admin_inputs_api.models.Timezone;

public interface TimezoneRepository extends JpaRepository<Timezone, Integer> {
    List<Timezone> findAll();
    Page<Timezone> findAll(Pageable pageable);
}
