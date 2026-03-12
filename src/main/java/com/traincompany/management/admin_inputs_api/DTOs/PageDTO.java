package com.traincompany.management.admin_inputs_api.DTOs;

import java.util.List;

public record PageDTO<T>(List<T> items, Long totalCount, Boolean hasNextPage) {};
