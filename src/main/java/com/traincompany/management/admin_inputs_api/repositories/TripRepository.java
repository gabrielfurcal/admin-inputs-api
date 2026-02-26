package com.traincompany.management.admin_inputs_api.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.traincompany.management.admin_inputs_api.models.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    public List<Trip> findAllByStatusId(Integer statusId);

    @Query("""
            SELECT t
            FROM Trip t
            JOIN Schedule s ON t.scheduleId = s.id
            WHERE (s.route.startStationId = :departureStationId)
            AND (s.route.endStationId = :arrivalStationId)
            AND (t.startTime >= :startTime)
            AND (t.endTime <= :endTime)
            """)
    public List<Trip> findFiltered(
        @Param("departureStationId") Integer departureStationId, 
        @Param("arrivalStationId") Integer arrivalStationId,
        @Param("startTime") Date startTime,
        @Param("endTime") Date endTime
    );
}
