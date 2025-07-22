package com.traincompany.management.admin_inputs_api.DTOs;

import java.util.List;

public record EmployeeDTO(Integer id, String firstName, String lastName, String position, 
                          String phoneNumber, String email, List<ScheduleDTO> schedules) { }
