package com.traincompany.management.admin_inputs_api.DTOs;

import java.util.List;

public record RouteDTO (Integer id, Integer startStationId, Integer endStationId, Float distance, List<StationDTO> stations) {}
