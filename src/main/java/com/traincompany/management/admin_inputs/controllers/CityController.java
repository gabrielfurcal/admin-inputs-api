package com.traincompany.management.admin_inputs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.traincompany.management.admin_inputs.DTOs.CityDTO;
import com.traincompany.management.admin_inputs.services.CityService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
// import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/cities/")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping()
    public ResponseEntity<List<CityDTO>> getCities() {
        try {
            return ResponseEntity.ok(cityService.findAll());
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
    public List<CityDTO> cities() throws Exception {
        return cityService.findAll();
    }

    @QueryMapping
    public CityDTO cityById(@Argument int id) throws Exception {
        return cityService.findById(id);
    }

    // @SchemaMapping(field = "schedules", typeName = "Status")
    // public List<ScheduleDTO> schedules(StatusDTO status) throws Exception {
    //     return scheduleService.findAll(status.id());
    // }
}