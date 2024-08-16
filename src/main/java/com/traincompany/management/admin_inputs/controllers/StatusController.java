package com.traincompany.management.admin_inputs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.services.StatusService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/status/")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping()
    public ResponseEntity<List<StatusDTO>> getMethodName() {
        try {
            return ResponseEntity.ok(statusService.findAll());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}