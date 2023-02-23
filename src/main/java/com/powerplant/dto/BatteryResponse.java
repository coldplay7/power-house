package com.powerplant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BatteryResponse {
    @JsonProperty(value = "avg_watt_capacity")
    private Double avgWattCapacity;
    @JsonProperty(value = "total_watt_capacity")
    private Double totalWattCapacity;
    private List<Battery> batteries;

}
