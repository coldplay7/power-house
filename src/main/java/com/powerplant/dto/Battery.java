package com.powerplant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Battery {

    @NotBlank
    private String name;
    @NotNull
    @JsonProperty(value = "post_code")
    private Integer postCode;
    @NotNull
    @JsonProperty(value = "watt_capacity")
    private Double wattCapacity;
}
