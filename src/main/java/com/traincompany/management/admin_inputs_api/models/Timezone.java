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
@Table(name="Timezones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Timezone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Timezone_ID")
    private Integer id;

    @Column(name = "Timezone_Name", length = 100)
    private String name;

    @Column(name = "Region", length = 50)
    private String region;
}
