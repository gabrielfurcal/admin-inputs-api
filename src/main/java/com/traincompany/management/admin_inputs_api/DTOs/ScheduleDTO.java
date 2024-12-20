package com.traincompany.management.admin_inputs_api.DTOs;

import java.util.List;

public record ScheduleDTO(Integer id, Integer trainId, Integer routeId, Integer statusId, String departureTime, String arrivalTime, List<EmployeeDTO> employees) { }