package com.traincompany.management.admin_inputs.controllers;

import java.util.List;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.services.StatusService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GraphQLController {
    private final StatusService statusService;

    @QueryMapping
    public List<StatusDTO> status() {
        try {
            return statusService.findAll();
        } catch (Exception ex) {
            return null;
        }
    }
}
