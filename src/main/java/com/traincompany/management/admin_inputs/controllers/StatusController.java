package com.traincompany.management.admin_inputs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.services.StatusService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/status/")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping()
    public ResponseEntity<List<StatusDTO>> getStatus() {
        try {
            return ResponseEntity.ok(statusService.findAll());
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
    public List<StatusDTO> status() throws Exception {
        return statusService.findAll();
    }

    @QueryMapping
    public StatusDTO statusById(@Argument int id) throws Exception {
        return statusService.findById(id);
    }
}