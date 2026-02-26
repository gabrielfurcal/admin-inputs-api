package com.traincompany.management.admin_inputs_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traincompany.management.admin_inputs_api.DTOs.RouteDTO;
import com.traincompany.management.admin_inputs_api.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs_api.DTOs.WeekdayDTO;
import com.traincompany.management.admin_inputs_api.services.RouteService;
import com.traincompany.management.admin_inputs_api.services.ScheduleService;
import com.traincompany.management.admin_inputs_api.services.WeekdayService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/schedules/")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final WeekdayService weekdayService;
    private final RouteService routeService;

    @GetMapping()
    public ResponseEntity<List<ScheduleDTO>> getSchedules() {
        try {
            return ResponseEntity.ok(scheduleService.findAll());
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
    public List<ScheduleDTO> schedules() throws Exception {
        return scheduleService.findAll();
    }

    @QueryMapping
    public ScheduleDTO scheduleById(@Argument Integer id) throws Exception {
        return scheduleService.findById(id);
    }

    @QueryMapping
    public List<ScheduleDTO> schedulesFiltered(@Argument Integer startStationId, 
                                         @Argument Integer endStationId, 
                                         @Argument String startDate, 
                                         @Argument String endDate, 
                                         @Argument Integer passengers) throws Exception {
        return scheduleService.findFiltered(startStationId, endStationId, startDate, endDate, passengers);
    }

    @SchemaMapping(typeName = "Schedule")
    public RouteDTO route(ScheduleDTO schedule) throws Exception {
        return routeService.findById(schedule.routeId());
    }

    @SchemaMapping(typeName = "Schedule")
    public WeekdayDTO departureWeekday(ScheduleDTO schedule) throws Exception {
        return weekdayService.findById(schedule.departureWeekdayId());
    }

    @SchemaMapping(typeName = "Schedule")
    public WeekdayDTO arrivalWeekday(ScheduleDTO schedule) throws Exception {
        return weekdayService.findById(schedule.arrivalWeekdayId());
    }

    @MutationMapping
    public ScheduleDTO saveSchedule(@Argument ScheduleDTO schedule) throws Exception {
        return scheduleService.save(schedule);
    }

    @MutationMapping
    public Boolean deleteSchedule(@Argument Integer id) throws Exception {
        return scheduleService.deleteById(id);
    }
}