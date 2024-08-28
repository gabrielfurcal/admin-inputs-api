package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.CityDTO;
import com.traincompany.management.admin_inputs.models.City;
import com.traincompany.management.admin_inputs.repositories.CityRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {
    private final CityRepository cityRepository;
    private final Mapper mapper;

    public List<CityDTO> findAll() throws Exception {
        try {
            var dbCities = cityRepository.findAll();
            var citiesList = dbCities.stream().map(city -> mapper.map(city)).toList();

            return citiesList;
        } catch (Exception ex) {
            log.error("Error at getting cities: {}", ex.getMessage());
            throw new Exception("Error at getting cities");
        }
    }

    public CityDTO findById(int id) throws Exception {
        try {
            City dbCity = cityRepository.findById(id).orElseThrow(() -> new Exception("City not found"));
            var city = mapper.map(dbCity);
            
            return city;
        } catch (Exception ex) {
            log.error("Error at getting city: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}