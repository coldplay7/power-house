package com.powerplant.mapper;

import com.powerplant.dto.Battery;
import com.powerplant.entity.BatteryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatteryMapper {
    BatteryEntity entity(Battery battery);
    Battery battery(BatteryEntity entity);
}
