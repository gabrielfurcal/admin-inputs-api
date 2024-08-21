package com.traincompany.management.admin_inputs.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.models.Status;
import com.traincompany.management.admin_inputs.repositories.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {
    private final StatusRepository statusRepository;

    public List<StatusDTO> findAll() throws Exception {
        try {
            var dbStatus = statusRepository.findAll();
            var statusList = new ArrayList<StatusDTO>();
            dbStatus.forEach(status -> statusList.add(new StatusDTO(status.getId(), status.getName(), status.getDescription())));

            return statusList;
        } catch (Exception ex) {
            log.error("Error at getting status: {}", ex.getMessage());
            throw new Exception("Error at getting status");
        }
    }

    public StatusDTO findById(int id) throws Exception {
        try {
            Status dbStatus = statusRepository.findById(id).orElseThrow(() -> new Exception("Status not found"));
            var status = new StatusDTO(dbStatus.getId(), dbStatus.getName(), dbStatus.getDescription());
            
            return status;
        } catch (Exception ex) {
            log.error("Error at getting status: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}