package com.traincompany.management.admin_inputs.DTOs;

import java.util.List;

public record ScheduleDTO(int id, int trainId, int routeId, int statusId, String departureTime, String arrivalTime, List<EmployeeDTO> employees) { }