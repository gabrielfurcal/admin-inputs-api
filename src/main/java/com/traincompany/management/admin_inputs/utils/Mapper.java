package com.traincompany.management.admin_inputs.utils;

import org.springframework.stereotype.Component;
import com.traincompany.management.admin_inputs.DTOs.CityDTO;
import com.traincompany.management.admin_inputs.DTOs.EmployeeDTO;
import com.traincompany.management.admin_inputs.DTOs.RouteDTO;
import com.traincompany.management.admin_inputs.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs.DTOs.StationDTO;
import com.traincompany.management.admin_inputs.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs.DTOs.TrainDTO;
import com.traincompany.management.admin_inputs.models.City;
import com.traincompany.management.admin_inputs.models.Employee;
import com.traincompany.management.admin_inputs.models.Route;
import com.traincompany.management.admin_inputs.models.Schedule;
import com.traincompany.management.admin_inputs.models.Station;
import com.traincompany.management.admin_inputs.models.Status;
import com.traincompany.management.admin_inputs.models.Train;

@Component
public class Mapper {
    public ScheduleDTO map(Schedule schedule) {
        return new ScheduleDTO(
                    schedule.getId(), 
                    schedule.getTrainId(), 
                    0,
                    schedule.getStatusId(),
                    DateFormatter.format(schedule.getDepartureTime(), "yyyy-MM-dd hh:mm:ss"),
                    DateFormatter.format(schedule.getArrivalTime(), "yyyy-MM-dd hh:mm:ss"),
                    null);
    }

    public EmployeeDTO map(Employee employee) {
        return new EmployeeDTO(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getPosition(),
            employee.getPhoneNumber(),
            employee.getEmail(),
            null
        );
    }

    public StationDTO map(Station station) {
        return new StationDTO(
            station.getId(),
            station.getName(),
            station.getCountryCode(),
            station.getPhone(),
            station.getPostalCode(),
            station.getLatitude(),
            station.getLongitude(),
            station.getCityId(),
            station.getImageUrl(),
            null
        );
    }

    public RouteDTO map(Route route) {
        return new RouteDTO(
            route.getId(),
            route.getStartStationId(),
            route.getEndStationId(),
            route.getDistance(),
            null
        );
    }

    public StatusDTO map(Status status) {
        return new StatusDTO(status.getId(), status.getName(), status.getDescription());
    }

    public TrainDTO map(Train train) {
        return new TrainDTO(train.getId(), train.getType(), train.getCapacity(), train.getMaxSpeed());
    }

    public CityDTO map(City city) {
        return new CityDTO(city.getId(), city.getCity(), city.getProvince(), city.getCountry());
    }
}