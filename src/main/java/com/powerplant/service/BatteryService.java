package com.powerplant.service;

import com.powerplant.dto.Battery;
import com.powerplant.dto.BatteryResponse;

import java.util.List;

public interface BatteryService {
    void save(List<Battery> batteries);

    BatteryResponse list(Integer from, Integer to);
}
