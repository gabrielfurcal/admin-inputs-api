package com.traincompany.management.admin_inputs_api.utils;

import org.springframework.stereotype.Component;
import com.traincompany.management.admin_inputs_api.DTOs.CityDTO;
import com.traincompany.management.admin_inputs_api.DTOs.EmployeeDTO;
import com.traincompany.management.admin_inputs_api.DTOs.RouteDTO;
import com.traincompany.management.admin_inputs_api.DTOs.ScheduleDTO;
import com.traincompany.management.admin_inputs_api.DTOs.StationDTO;
import com.traincompany.management.admin_inputs_api.DTOs.StatusDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TimezoneDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TrainDTO;
import com.traincompany.management.admin_inputs_api.DTOs.TripDTO;
import com.traincompany.management.admin_inputs_api.DTOs.WeekdayDTO;
import com.traincompany.management.admin_inputs_api.models.City;
import com.traincompany.management.admin_inputs_api.models.Employee;
import com.traincompany.management.admin_inputs_api.models.Route;
import com.traincompany.management.admin_inputs_api.models.Schedule;
import com.traincompany.management.admin_inputs_api.models.Station;
import com.traincompany.management.admin_inputs_api.models.Status;
import com.traincompany.management.admin_inputs_api.models.Timezone;
import com.traincompany.management.admin_inputs_api.models.Train;
import com.traincompany.management.admin_inputs_api.models.Trip;
import com.traincompany.management.admin_inputs_api.models.Weekday;

@Component
public class Mapper {
    /*
    * Schedule Mappers
    */
    public ScheduleDTO map(Schedule schedule) {
        return new ScheduleDTO(
                    schedule.getId(),
                    schedule.getRouteId(),
                    schedule.getDepartureWeekdayId(),
                    DateAndTimeFormatter.toString(schedule.getDepartureTime(), "HH:mm:ss"),
                    schedule.getArrivalWeekdayId(),
                    DateAndTimeFormatter.toString(schedule.getArrivalTime(), "HH:mm:ss"));
    }

    public Schedule map(ScheduleDTO schedule) throws Exception {
        return new Schedule(
                    schedule.id(), 
                    schedule.departureWeekdayId(),
                    DateAndTimeFormatter.toTime(schedule.departureTime(), "HH:mm:ss"),
                    schedule.arrivalWeekdayId(),
                    DateAndTimeFormatter.toTime(schedule.arrivalTime(), "HH:mm:ss"),
                    schedule.routeId(),
                    null,
                    null,
                    null);
    }
    
    /*
    * Trip Mappers
    */
    public TripDTO map(Trip trip) {
        return new TripDTO(
                    trip.getId(),
                    trip.getScheduleId(),
                    trip.getTrainId(),
                    trip.getStatusId(),
                    DateAndTimeFormatter.toString(trip.getStartTime(), "yyyy-MM-dd HH:mm:ss"),
                    DateAndTimeFormatter.toString(trip.getEndTime(), "yyyy-MM-dd HH:mm:ss"),
                    null);
    }

    public Trip map(TripDTO trip) throws Exception {
        return new Trip(
                    trip.id(), 
                    trip.scheduleId(),
                    trip.trainId(),
                    trip.statusId(),
                    DateAndTimeFormatter.toDate(trip.startTime(), "yyyy-MM-dd HH:mm:ss"),
                    DateAndTimeFormatter.toDate(trip.endTime(), "yyyy-MM-dd HH:mm:ss"),
                    null,
                    null,
                    null);
    }

    /*
    * Employee Mappers
    */
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

    public Employee map(EmployeeDTO employee) {
        return new Employee(
            employee.id(),
            employee.firstName(),
            employee.lastName(),
            employee.position(),
            employee.phoneNumber(),
            employee.email()
        );
    }

    /*
    * Station Mappers
    */
    public StationDTO map(Station station) {
        return new StationDTO(
            station.getId(),
            station.getName(),
            station.getCountryCode(),
            station.getPhone(),
            station.getPostalCode(),
            station.getLatitude(),
            station.getLongitude(),
            station.getTimezoneId(),
            station.getCityId(),
            station.getImageUrl(),
            null
        );
    }

    public Station map(StationDTO station) {
        return new Station(
            station.id(),
            station.name(),
            station.countryCode(),
            station.phone(),
            station.postalCode(),
            station.latitude(),
            station.longitude(),
            station.timezoneId(),
            station.cityId(),
            station.imageUrl(),
            null,
            null
        );
    }

    /*
    * Route Mappers
    */
    public RouteDTO map(Route route) {
        return new RouteDTO(
            route.getId(),
            route.getStartStationId(),
            route.getEndStationId(),
            route.getDistance(),
            null
        );
    }

    public Route map(RouteDTO route) {
        return new Route(
            route.id(),
            route.startStationId(),
            route.endStationId(),
            route.distance(),
            null,
            null
        );
    }

    /*
    * Status Mappers
    */
    public StatusDTO map(Status status) {
        return new StatusDTO(status.getId(), status.getName(), status.getDescription());
    }

    public Status map(StatusDTO status) {
        return new Status(status.id(), status.name(), status.description());
    }

    /*
    * Train Mappers
    */
    public TrainDTO map(Train train) {
        return new TrainDTO(train.getId(), train.getType(), train.getCapacity(), train.getMaxSpeed());
    }

    public Train map(TrainDTO train) {
        return new Train(train.id(), train.type(), train.capacity(), train.maxSpeed());
    }

    /*
    * City Mappers
    */
    public CityDTO map(City city) {
        return new CityDTO(city.getId(), city.getCity(), city.getProvince(), city.getCountry());
    }

    public City map(CityDTO city) {
        return new City(city.id(), city.city(), city.province(), city.country());
    }

    /*
    * Timezone Mappers
    */
    public TimezoneDTO map(Timezone timezone) {
        return new TimezoneDTO(timezone.getId(), timezone.getName(), timezone.getRegion());
    }

    public Timezone map(TimezoneDTO timezone) {
        return new Timezone(timezone.id(), timezone.name(), timezone.region());
    }

    /*
    * Weekday Mappers
    */
    public WeekdayDTO map(Weekday weekday) {
        return new WeekdayDTO(weekday.getId(), weekday.getName());
    }

    public Weekday map(WeekdayDTO weekday) {
        return new Weekday(weekday.id(), weekday.name());
    }
}