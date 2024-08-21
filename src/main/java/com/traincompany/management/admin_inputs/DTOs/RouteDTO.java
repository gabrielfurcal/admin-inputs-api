package com.traincompany.management.admin_inputs.DTOs;

import java.util.List;

public record RouteDTO (int id, int startStationId, int endStationId, float distance, List<StationDTO> stations) {}
