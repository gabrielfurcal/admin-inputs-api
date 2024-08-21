package com.traincompany.management.admin_inputs.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs.models.Schedule;
// import com.traincompany.management.admin_inputs.repositories.StatusRepository;
import com.traincompany.management.admin_inputs.utils.DateFormatter;
import com.traincompany.management.admin_inputs.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    // private final StatusRepository statusRepository;
    private final ScheduleRepository scheduleRepository;

    public List<ScheduleDTO> findAll() throws Exception {
        try {
            var dbSchedules = scheduleRepository.findAll();
            var scheduleList = new ArrayList<ScheduleDTO>();
            
            dbSchedules.forEach(schedule -> scheduleList.add(
                new ScheduleDTO(
                    schedule.getId(), 
                    schedule.getTrainId(), 
                    0,
                    schedule.getStatusId(),
                    DateFormatter.format(schedule.getDepartureTime(), "yyyy-MM-dd hh:mm:ss"),
                    DateFormatter.format(schedule.getArrivalTime(), "yyyy-MM-dd hh:mm:ss"),
                    null)));

            return scheduleList;
        } catch (Exception ex) {
            log.error("Error at getting schedules: {}", ex.getMessage());
            throw new Exception("Error at getting schedules");
        }
    }

    public ScheduleDTO findById(int id) throws Exception {
        try {
            Schedule dbSchedule = scheduleRepository.findById(id).orElseThrow(() -> new Exception("Schedule not found"));
            
            var schedule = new ScheduleDTO(
                dbSchedule.getId(), 
                dbSchedule.getTrainId(), 
                0,
                dbSchedule.getStatusId(),
                DateFormatter.format(dbSchedule.getDepartureTime(), "yyyy-MM-dd hh:mm:ss"),
                DateFormatter.format(dbSchedule.getArrivalTime(), "yyyy-MM-dd hh:mm:ss"),
                null);
            
            return schedule;
        } catch (Exception ex) {
            log.error("Error at getting schedule: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}