package com.traincompany.management.admin_inputs_api.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.traincompany.management.admin_inputs_api.DTOs.PageDTO;
import com.traincompany.management.admin_inputs_api.DTOs.WeekdayDTO;
import com.traincompany.management.admin_inputs_api.repositories.WeekdayRepository;
import com.traincompany.management.admin_inputs_api.utils.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeekdayService {
    private final WeekdayRepository weekdayRepository;
    private final Mapper mapper;

    public List<WeekdayDTO> findAll() throws Exception {
        try {
            var dbWeekdays = weekdayRepository.findAll();
            var weekdaysList = dbWeekdays.stream().map(weekday -> mapper.map(weekday)).toList();

            return weekdaysList;
        } catch (Exception ex) {
            log.error("Error at getting weekdays: {}", ex.getMessage());
            throw new Exception("Error at getting weekdays");
        }
    }

    public PageDTO<WeekdayDTO> findAll(Integer offset, Integer limit) throws Exception {
        try {
            var pageable = PageRequest.of(offset, limit);
            var weekdaysPage = weekdayRepository.findAll(pageable);
            var weekdaysList = weekdaysPage.getContent().stream().map(weekday -> mapper.map(weekday)).toList();

            return new PageDTO<WeekdayDTO>(weekdaysList, weekdayRepository.count(), weekdaysPage.hasNext());
        } catch (Exception ex) {
            log.error("Error at getting weekdays: {}", ex.getMessage());
            throw new Exception("Error at getting weekdays");
        }
    }

    public WeekdayDTO findById(Integer id) throws Exception {
        try {
            var dbWeekday = weekdayRepository.findById(id).orElseThrow(() -> new Exception("Weekday not found"));
            var weekday = mapper.map(dbWeekday);
            
            return weekday;
        } catch (Exception ex) {
            log.error("Error at getting weekday: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public WeekdayDTO save(WeekdayDTO weekday) throws Exception {
        try {
            if(weekday.id() == null) {
                var weekdayToSave = mapper.map(weekday);
                weekdayToSave = weekdayRepository.save(weekdayToSave);

                return mapper.map(weekdayToSave);
            } else {
                var weekdayToUpdate = weekdayRepository.findById(weekday.id()).get();
                weekdayToUpdate.setName(weekday.name());        

                weekdayToUpdate = weekdayRepository.save(weekdayToUpdate);

                return mapper.map(weekdayToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving weekday: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            var weekdayToDelete = weekdayRepository.findById(id).get();

            weekdayRepository.delete(weekdayToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting weekday with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}