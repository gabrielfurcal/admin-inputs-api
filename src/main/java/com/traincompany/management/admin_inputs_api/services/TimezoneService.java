package com.traincompany.management.admin_inputs_api.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.traincompany.management.admin_inputs_api.DTOs.PageDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TimezoneDTO;
import com.traincompany.management.admin_inputs_api.repositories.TimezoneRepository;
import com.traincompany.management.admin_inputs_api.utils.Mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimezoneService {
    private final TimezoneRepository timezoneRepository;
    private final Mapper mapper;

    public List<TimezoneDTO> findAll() throws Exception {
        try {
            var dbTimezones = timezoneRepository.findAll();
            var timezonesList = dbTimezones.stream().map(timezone -> mapper.map(timezone)).toList();

            return timezonesList;
        } catch (Exception ex) {
            log.error("Error at getting timezones: {}", ex.getMessage());
            throw new Exception("Error at getting timezones");
        }
    }

    public PageDTO<TimezoneDTO> findAll(Integer offset, Integer limit) throws Exception {
        try {
            var pageable = PageRequest.of(offset, limit);
            var timezonesPage = timezoneRepository.findAll(pageable);
            var timezonesList = timezonesPage.getContent().stream().map(timezone -> mapper.map(timezone)).toList();

            return new PageDTO<TimezoneDTO>(timezonesList, timezoneRepository.count(), timezonesPage.hasNext());
        } catch (Exception ex) {
            log.error("Error at getting timezones: {}", ex.getMessage());
            throw new Exception("Error at getting timezones");
        }
    }

    public TimezoneDTO findById(Integer id) throws Exception {
        try {
            var dbTimezone = timezoneRepository.findById(id).orElseThrow(() -> new Exception("Timezone not found"));
            var timezone = mapper.map(dbTimezone);
            
            return timezone;
        } catch (Exception ex) {
            log.error("Error at getting timezone: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public TimezoneDTO save(TimezoneDTO timezone) throws Exception {
        try {
            if(timezone.id() == null) {
                var timezoneToSave = mapper.map(timezone);
                timezoneToSave = timezoneRepository.save(timezoneToSave);

                return mapper.map(timezoneToSave);
            } else {
                var timezoneToUpdate = timezoneRepository.findById(timezone.id()).get();
                timezoneToUpdate.setName(timezone.name());
                timezoneToUpdate.setRegion(timezone.region());

                timezoneToUpdate = timezoneRepository.save(timezoneToUpdate);

                return mapper.map(timezoneToUpdate);
            }
        } catch(Exception ex) {
            log.error("Error at saving timezone: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Boolean deleteById(Integer id) throws Exception {
        try {
            var timezoneToDelete = timezoneRepository.findById(id).get();

            timezoneRepository.delete(timezoneToDelete);
            
            return true;
        } catch(Exception ex) {
            log.error("Error at deleting timezone with ID: " + id.toString(), ex);
            throw new Exception(ex.getMessage());
        }
    }
}