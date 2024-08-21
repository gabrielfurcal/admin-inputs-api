package com.traincompany.management.admin_inputs.DTOs;

import java.util.List;

public record EmployeeDTO(int id, String firstName, String lastName, String position, 
                          String phoneNumber, String email, List<ScheduleDTO> schedules) { }
