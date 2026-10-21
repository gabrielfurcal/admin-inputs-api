package com.traincompany.management.admin_inputs_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traincompany.management.admin_inputs_api.DTOs.PageDTO;
import com.traincompany.management.admin_inputs_api.DTOs.WeekdayDTO;
import com.traincompany.management.admin_inputs_api.services.WeekdayService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/weekdays/")
@RequiredArgsConstructor
public class WeekdayController {
    private final WeekdayService weekdayService;

    @GetMapping()
    public ResponseEntity<List<WeekdayDTO>> getWeekdays() {
        try {
            return ResponseEntity.ok(weekdayService.findAll());
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
    public List<WeekdayDTO> weekdays() throws Exception {
        return weekdayService.findAll();
    }

    @QueryMapping
    public PageDTO<WeekdayDTO> weekdaysPage(@Argument Integer offset, @Argument Integer limit) throws Exception {
        return weekdayService.findAll(offset, limit);
    }

    @QueryMapping
    public WeekdayDTO weekdayById(@Argument Integer id) throws Exception {
        return weekdayService.findById(id);
    }

    @MutationMapping
    public WeekdayDTO saveWeekday(@Argument WeekdayDTO weekday) throws Exception {
        return weekdayService.save(weekday);
    }

    @MutationMapping
    public Boolean deleteWeekday(@Argument Integer id) throws Exception {
        return weekdayService.deleteById(id);
    }
}