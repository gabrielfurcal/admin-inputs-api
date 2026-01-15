package com.traincompany.management.admin_inputs_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.traincompany.management.admin_inputs_api.models.Schedule;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public List<Schedule> findAllByStatusId(Integer statusId);

    @Query("""
            SELECT s
            FROM Schedule s
            WHERE (s.route.startStationId = :startStationId)
            AND (s.route.endStationId = :endStationId)
            AND (s.departureTime = :startDate)
            OR (s.departureTime = :endDate)
            """)
    public List<Schedule> findFiltered(
        @Param("startStationId") Integer startStationId, 
        @Param("endStationId") Integer endStationId, 
        @Param("startDate") java.util.Date startDate, 
        @Param("endDate") java.util.Date endDate);
}
