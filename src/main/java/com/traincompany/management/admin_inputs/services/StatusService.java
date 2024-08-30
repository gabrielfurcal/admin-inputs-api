package com.traincompany.management.admin_inputs.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.models.Status;
import com.traincompany.management.admin_inputs.repositories.StatusRepository;
import com.traincompany.management.admin_inputs.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {
    private final StatusRepository statusRepository;
    private final Mapper mapper;

    public List<StatusDTO> findAll() throws Exception {
        try {
            var dbStatus = statusRepository.findAll();
            var statusList = dbStatus.stream().map(status -> mapper.map(status)).toList();

            return statusList;
        } catch (Exception ex) {
            log.error("Error at getting status: {}", ex.getMessage());
            throw new Exception("Error at getting status");
        }
    }

    public StatusDTO findById(Integer id) throws Exception {
        try {
            Status dbStatus = statusRepository.findById(id).orElseThrow(() -> new Exception("Status not found"));
            var status = mapper.map(dbStatus);
            
            return status;
        } catch (Exception ex) {
            log.error("Error at getting status: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
}