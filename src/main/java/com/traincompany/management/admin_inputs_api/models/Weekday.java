package com.traincompany.management.admin_inputs_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Weekdays")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weekday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Weekday_ID")
    private Integer id;

    @Column(name = "Name", length = 10)
    private String name;
}
