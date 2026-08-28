package com.traincompany.management.admin_inputs_api.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.traincompany.management.admin_inputs_api.DTOs.PageDTO;
import com.traincompany.management.admin_inputs_api.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs_api.models.Schedule;
import com.traincompany.management.admin_inputs_api.repositories.RouteRepository;
import com.traincompany.management.admin_inputs_api.repositories.ScheduleRepository;
import com.traincompany.management.admin_inputs_api.utils.DateAndTimeFormatter;
import com.traincompany.management.admin_inputs_api.utils.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final RouteRepository routeRepository;
    private final Mapper mapper;

    public List<ScheduleDTO> findAll() throws Exception {
        try {
            var dbSchedules = scheduleRepository.findAll();
            var scheduleList = dbSchedules.stream().map(schedule -> mapper.map(schedule)).toList();
            
            return scheduleList;
        } catch (Exception ex) {
            log.error("Error at getting schedules: {}", ex.getMessage());
            throw new Exception("Error at getting schedules");
        }
    }

    public PageDTO<ScheduleDTO> findAll(Integer offset, Integer limit) throws Exception {
        try {
            var pageable = PageRequest.of(offset, limit);
            var schedulesPage = scheduleRepository.findAll(pageable);
            var scheduleList = schedulesPage.getContent().stream().map(schedule -> mapper.map(schedule)).toList();

            return new PageDTO<ScheduleDTO>(scheduleList, scheduleRepository.count(), schedulesPage.hasNext());
        } catch (Exception ex) {
            log.error("Error at getting schedules: {}", ex.getMessage());
            throw new Exception("Error at getting schedules");
        }
    }

    public List<ScheduleDTO> findFiltered(Integer departureStationId, Integer arrivalStationId, String departureTime, String arrivalTime, Integer passengers) throws Exception {
        try {
            var dbSchedules = scheduleRepository.findFiltered(departureStationId, arrivalStationId, DateAndTimeFormatter.toTime(departureTime, "HH:mm:ss"), DateAndTimeFormatter.toTime(arrivalTime, "HH:mm:ss"));
            var scheduleList = dbSchedules.stream().map(schedule -> mapper.map(schedule)).toList();

            return scheduleList;
        } catch (Exception ex) {
            log.error("Error at getting filtered schedules: {}", ex.getMessage());
            throw new Exception("Error at getting filtered schedules");
        }
    }

    public ScheduleDTO findById(Integer id) throws Exception {
        try {
            Schedule dbSchedule = scheduleRepository.findById(id).orElseThrow(() -> new Exception("Schedule not found"));
            var schedule = mapper.map(dbSchedule);
            
            return schedule;
        } catch (Exception ex) {
            log.error("Error at getting schedule: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public ScheduleDTO save(ScheduleDTO schedule) throws Exception {
        try {
            if(schedule.id() == null) {
                Schedule scheduleToSave = mapper.map(schedule);
                scheduleToSave.setRoute(routeRepository.findById(schedule.routeId()).get());

                scheduleToSave = scheduleRepository.save(scheduleToSave);

                return mapper.map(scheduleToSave);
            } else {
                Schedule scheduleToUpdate = scheduleRepository.findById(schedule.id()).get();
                scheduleToUpdate.setDepartureTime(DateAndTimeFormatter.toTime(schedule.departureTime(), "HH:mm:ss"));
                scheduleToUpdate.setArrivalTime(DateAndTimeFormatter.toTime(schedule.arrivalTime(), "HH:mm:ss"));
                scheduleToUpdate.setRouteId(schedule.routeId());
                scheduleToUpdate.setRoute(routeRepository.findById(schedule.routeId()).get());

                scheduleToUpdate = scheduleRepository.save(scheduleToUpdate);

                return mapper.map(scheduleToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving schedule: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            Schedule scheduleToDelete = scheduleRepository.findById(id).get();
            scheduleToDelete.setRoute(null);
            
            scheduleRepository.delete(scheduleToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting schedule with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}