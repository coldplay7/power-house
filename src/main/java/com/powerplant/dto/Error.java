package com.powerplant.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private String field;
    private String message;
}
