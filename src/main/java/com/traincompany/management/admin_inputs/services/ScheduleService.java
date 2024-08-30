package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs.models.Schedule;
import com.traincompany.management.admin_inputs.utils.Mapper;
import com.traincompany.management.admin_inputs.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
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

    public List<ScheduleDTO> findAll(Integer statusId) throws Exception {
        try {
            var dbSchedules = scheduleRepository.findAllByStatusId(statusId);
            var scheduleList = dbSchedules.stream().map(schedule -> mapper.map(schedule)).toList();

            return scheduleList;
        } catch (Exception ex) {
            log.error("Error at getting schedules: {}", ex.getMessage());
            throw new Exception("Error at getting schedules");
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
}