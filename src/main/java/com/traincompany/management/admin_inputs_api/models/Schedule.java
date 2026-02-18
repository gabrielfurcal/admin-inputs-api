package com.traincompany.management.admin_inputs_api.models;

import java.time.LocalTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Schedules")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Schedule_ID")
    private Integer id;

    @Column(name = "Departure_Weekday_ID", insertable = false, updatable = false)
    private Integer departureWeekdayId;

    @Column(name = "Departure_Time")
    private LocalTime departureTime;

    @Column(name = "Arrival_Weekday_ID", insertable = false, updatable = false)
    private Integer arrivalWeekdayId;

    @Column(name = "Arrival_Time")
    private LocalTime arrivalTime;

    @Column(name = "Route_ID", insertable = false, updatable = false)
    private Integer routeId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Route_ID")
    private Route route;
}
