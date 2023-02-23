package com.powerplant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(name = "BATTERY")
@Data
public class BatteryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(name = "post_code")
    private Integer postCode;
    @Column(name = "watt_capacity")
    private Integer wattCapacity;

}
