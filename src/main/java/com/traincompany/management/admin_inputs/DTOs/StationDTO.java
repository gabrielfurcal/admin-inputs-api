package com.traincompany.management.admin_inputs.DTOs;

import java.util.List;

public record StationDTO(int id, String name, String countryCode, String phone, String postalCode,
                        float latitude, float longitude, int cityId, String imageUrl, List<RouteDTO> routes) { }
