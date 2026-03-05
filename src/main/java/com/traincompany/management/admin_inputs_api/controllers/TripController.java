package com.traincompany.management.admin_inputs_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traincompany.management.admin_inputs_api.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs_api.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TrainDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TripDTO;
import com.traincompany.management.admin_inputs_api.services.ScheduleService;
import com.traincompany.management.admin_inputs_api.services.StatusService;
import com.traincompany.management.admin_inputs_api.services.TrainService;
import com.traincompany.management.admin_inputs_api.services.TripService;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/trips/")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    private final ScheduleService scheduleService;
    private final StatusService statusService;
    private final TrainService trainService;

    @GetMapping()
    public ResponseEntity<List<TripDTO>> getTrips() {
        try {
            return ResponseEntity.ok(tripService.findAll());
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
    public List<TripDTO> trips() throws Exception {
        return tripService.findAll();
    }

    @QueryMapping
    public TripDTO tripById(@Argument Integer id) throws Exception {
        return tripService.findById(id);
    }

    @QueryMapping
    public List<TripDTO> tripsFiltered(@Argument Integer startStationId, 
                                         @Argument Integer endStationId, 
                                         @Argument String startDate, 
                                         @Argument String endDate, 
                                         @Argument Integer passengers) throws Exception {
        return tripService.findFiltered(startStationId, endStationId, startDate, endDate, passengers);
    }

    @SchemaMapping(typeName = "Trip")
    public StatusDTO status(TripDTO trip) throws Exception {
        return statusService.findById(trip.statusId());
    }

    @SchemaMapping(typeName = "Trip")
    public ScheduleDTO schedule(TripDTO trip) throws Exception {
        return scheduleService.findById(trip.scheduleId());
    }

    @SchemaMapping(typeName = "Trip")
    public TrainDTO train(TripDTO trip) throws Exception {
        return trainService.findById(trip.trainId());
    }

    @MutationMapping
    public TripDTO saveTrip(@Argument TripDTO trip) throws Exception {
        return tripService.save(trip);
    }

    @MutationMapping
    public Boolean deleteTrip(@Argument Integer id) throws Exception {
        return tripService.deleteById(id);
    }
}