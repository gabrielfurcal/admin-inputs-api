package com.traincompany.management.admin_inputs_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traincompany.management.admin_inputs_api.DTOs.CityDTO;
import com.traincompany.management.admin_inputs_api.DTOs.StationDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TimezoneDTO;
import com.traincompany.management.admin_inputs_api.services.CityService;
import com.traincompany.management.admin_inputs_api.services.StationService;
import com.traincompany.management.admin_inputs_api.services.TimezoneService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/stations/")
@RequiredArgsConstructor
public class StationController {

    private final CityService cityService;
    private final StationService stationService;
    private final TimezoneService timezoneService;

    @GetMapping()
    public ResponseEntity<List<StationDTO>> getStations() {
        try {
            return ResponseEntity.ok(stationService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /*
    * 
    * GraphQL Endpoints
    * 
    */
    @QueryMapping
    public List<StationDTO> stations() throws Exception {
        return stationService.findAll();
    }

    @QueryMapping
    public StationDTO stationById(@Argument Integer id) throws Exception {
        return stationService.findById(id);
    }

    @SchemaMapping(typeName = "Station")
    public CityDTO city(StationDTO station) throws Exception {
        return cityService.findById(station.cityId());
    }
    
    @SchemaMapping(typeName = "Station")
    public TimezoneDTO timezone(StationDTO station) throws Exception {
        return timezoneService.findById(station.timezoneId());
    }

    @MutationMapping
    public StationDTO saveStation(@Argument StationDTO station) throws Exception {
        return stationService.save(station);
    }

    @MutationMapping
    public Boolean deleteStation(@Argument Integer id) throws Exception {
        return stationService.deleteById(id);
    }
}