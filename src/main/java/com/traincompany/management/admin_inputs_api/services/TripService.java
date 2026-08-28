package com.traincompany.management.admin_inputs_api.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.traincompany.management.admin_inputs_api.DTOs.PageDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TripDTO;
import com.traincompany.management.admin_inputs_api.models.Trip;
import com.traincompany.management.admin_inputs_api.repositories.StatusRepository;
import com.traincompany.management.admin_inputs_api.repositories.TrainRepository;
import com.traincompany.management.admin_inputs_api.repositories.TripRepository;
import com.traincompany.management.admin_inputs_api.utils.DateAndTimeFormatter;
import com.traincompany.management.admin_inputs_api.utils.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {
    private final TripRepository tripRepository;
    private final TrainRepository trainRepository;
    private final StatusRepository statusRepository;
    private final Mapper mapper;

    public List<TripDTO> findAll() throws Exception {
        try {
            var dbTrip = tripRepository.findAll();
            var tripList = dbTrip.stream().map(trip -> mapper.map(trip)).toList();
            
            return tripList;
        } catch (Exception ex) {
            log.error("Error at getting trips: {}", ex.getMessage());
            throw new Exception("Error at getting trips");
        }
    }

    public PageDTO<TripDTO> findAll(Integer offset, Integer limit) throws Exception {
        try {
            var pageable = PageRequest.of(offset, limit);
            var tripsPage = tripRepository.findAll(pageable);
            var tripList = tripsPage.getContent().stream().map(trip -> mapper.map(trip)).toList();

            return new PageDTO<TripDTO>(tripList, tripRepository.count(), tripsPage.hasNext());
        } catch (Exception ex) {
            log.error("Error at getting trips: {}", ex.getMessage());
            throw new Exception("Error at getting trips");
        }
    }

    public List<TripDTO> findAll(Integer statusId) throws Exception {
        try {
            var dbTrips = tripRepository.findAllByStatusId(statusId);
            var tripList = dbTrips.stream().map(trip -> mapper.map(trip)).toList();

            return tripList;
        } catch (Exception ex) {
            log.error("Error at getting trips: {}", ex.getMessage());
            throw new Exception("Error at getting trips");
        }
    }

    public List<TripDTO> findFiltered(Integer startStationId, Integer endStationId, String startDate, String endDate, Integer passengers) throws Exception {
        try {
            var dbTrips = tripRepository.findFiltered(startStationId, endStationId, DateAndTimeFormatter.toDate(startDate, "yyyy-MM-dd hh:mm:ss"), DateAndTimeFormatter.toDate(endDate, "yyyy-MM-dd hh:mm:ss"));
            var tripList = dbTrips.stream().map(trip -> mapper.map(trip)).toList();

            return tripList;
        } catch (Exception ex) {
            log.error("Error at getting filtered trips: {}", ex.getMessage());
            throw new Exception("Error at getting filtered trips");
        }
    }

    public TripDTO findById(Integer id) throws Exception {
        try {
            Trip dbTrip = tripRepository.findById(id).orElseThrow(() -> new Exception("Trip not found"));
            var trip = mapper.map(dbTrip);
            
            return trip;
        } catch (Exception ex) {
            log.error("Error at getting trip: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public TripDTO save(TripDTO trip) throws Exception {

        try {
            if(trip.id() == null) {
                Trip tripToSave = mapper.map(trip);
                tripToSave.setTrain(trainRepository.findById(trip.trainId()).get());
                tripToSave.setStatus(statusRepository.findById(trip.statusId()).get());

                tripToSave = tripRepository.save(tripToSave);

                return mapper.map(tripToSave);
            } else {
                Trip tripToUpdate = tripRepository.findById(trip.id()).get();
                tripToUpdate.setStartTime(DateAndTimeFormatter.toDate(trip.startTime(), "yyyy-MM-dd hh:mm:ss"));
                tripToUpdate.setEndTime((DateAndTimeFormatter.toDate(trip.endTime(), "yyyy-MM-dd hh:mm:ss")));
                tripToUpdate.setStatusId(trip.statusId());
                tripToUpdate.setTrainId(trip.trainId());
                tripToUpdate.setTrain(trainRepository.findById(trip.trainId()).get());
                tripToUpdate.setStatus(statusRepository.findById(trip.statusId()).get());

                tripToUpdate = tripRepository.save(tripToUpdate);

                return mapper.map(tripToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving trip: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            Trip tripToDelete = tripRepository.findById(id).get();
            tripToDelete.setTrain(null);
            tripToDelete.setStatus(null);
            
            tripRepository.delete(tripToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting trip with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}