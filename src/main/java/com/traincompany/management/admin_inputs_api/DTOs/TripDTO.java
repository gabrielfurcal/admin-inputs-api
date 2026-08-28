package com.traincompany.management.admin_inputs_api.DTOs;

import java.util.List;

public record TripDTO(Integer id, Integer scheduleId, Integer trainId, Integer statusId, String startTime, String endTime, List<EmployeeDTO> employees) { }