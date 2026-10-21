package com.traincompany.management.admin_inputs_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traincompany.management.admin_inputs_api.DTOs.PageDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TimezoneDTO;
import com.traincompany.management.admin_inputs_api.services.TimezoneService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/timezones/")
@RequiredArgsConstructor
public class TimezoneController {
    private final TimezoneService timezoneService;

    @GetMapping()
    public ResponseEntity<List<TimezoneDTO>> getTimezones() {
        try {
            return ResponseEntity.ok(timezoneService.findAll());
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
    public List<TimezoneDTO> timezones() throws Exception {
        return timezoneService.findAll();
    }

    @QueryMapping
    public PageDTO<TimezoneDTO> timezonesPage(@Argument Integer offset, @Argument Integer limit) throws Exception {
        return timezoneService.findAll(offset, limit);
    }

    @QueryMapping
    public TimezoneDTO timezoneById(@Argument Integer id) throws Exception {
        return timezoneService.findById(id);
    }

    @MutationMapping
    public TimezoneDTO saveTimezone(@Argument TimezoneDTO timezone) throws Exception {
        return timezoneService.save(timezone);
    }

    @MutationMapping
    public Boolean deleteTimezone(@Argument Integer id) throws Exception {
        return timezoneService.deleteById(id);
    }
}