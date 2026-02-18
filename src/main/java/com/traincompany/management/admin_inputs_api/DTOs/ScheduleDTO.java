package com.traincompany.management.admin_inputs_api.DTOs;

public record ScheduleDTO(Integer id, Integer routeId, Integer departureWeekdayId, String departureTime, Integer arrivalWeekdayId, String arrivalTime) { }