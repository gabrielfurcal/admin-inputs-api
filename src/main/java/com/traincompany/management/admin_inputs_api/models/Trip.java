package com.traincompany.management.admin_inputs_api.models;

import java.util.Date;
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
@Table(name="Trips")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Trip_ID")
    private Integer id;

    @Column(name = "Schedule_ID", insertable = false, updatable = false)
    private Integer scheduleId;

    @Column(name = "Train_ID", insertable = false, updatable = false)
    private Integer trainId;

    @Column(name = "Status_ID", insertable = false, updatable = false)
    private Integer statusId;

    @Column(name = "Start_Time")
    private Date startTime;

    @Column(name = "End_Time")
    private Date endTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Schedule_ID")
    private Schedule schedule;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Train_ID")
    private Train train;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="Status_ID")
    private Status status;
}
