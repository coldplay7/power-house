package com.powerplant.repository;

import com.powerplant.entity.BatteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteryRepository extends JpaRepository<BatteryEntity, Long> {
    List<BatteryEntity> findAllByPostCodeBetween(Integer from, Integer to);
}

