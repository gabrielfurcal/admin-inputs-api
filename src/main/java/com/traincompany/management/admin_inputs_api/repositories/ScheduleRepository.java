package com.traincompany.management.admin_inputs_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.traincompany.management.admin_inputs_api.models.Schedule;

import java.time.LocalTime;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public List<Schedule> findAllByStatusId(Integer statusId);

    @Query("""
            SELECT s
            FROM Schedule s
            WHERE (s.departureStationId = :departureStationId)
            AND (s.arrivalStationId = :arrivalStationId)
            AND (s.departureTime = :departureTime)
            AND (s.arrivalTime = :arrivalTime)
            """)
    public List<Schedule> findFiltered(
        @Param("departureStationId") Integer departureStationId, 
        @Param("arrivalStationId") Integer arrivalStationId, 
        @Param("departureTime") LocalTime departureTime, 
        @Param("arrivalTime") LocalTime arrivalTime);
}
