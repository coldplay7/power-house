package com.powerplant.service;

import com.powerplant.dto.Battery;
import com.powerplant.dto.BatteryResponse;
import com.powerplant.mapper.BatteryMapper;
import com.powerplant.repository.BatteryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
@AllArgsConstructor
@Slf4j
public class BatteryServiceImpl implements BatteryService {

    private final BatteryRepository repository;
    private final BatteryMapper mapper;

    @Override
    public void save(List<Battery> batteries) {
        repository.saveAll(batteries.stream()
                .map(mapper::entity)
                .collect(Collectors.toList()));
        log.info("Batteries with count {} success saved.", batteries.size());
    }

    @Override
    public BatteryResponse list(Integer from, Integer to) {
        log.info("Querying post code range from: {} to: {}", from, to);
        var batteries = repository.findAllByPostCodeBetween(from, to)
                .stream()
                .map(mapper::battery)
                .sorted(comparing(Battery::getName))
                .collect(Collectors.toList());

        double totalWattCapacity = batteries.stream()
                .mapToDouble(Battery::getWattCapacity)
                .sum();

        var response = BatteryResponse.builder()
                .batteries(batteries)
                .totalWattCapacity(totalWattCapacity)
                .avgWattCapacity(batteries.size() > 0 ? totalWattCapacity / batteries.size() : 0)
                .build();
        log.debug("Battery response: {}", response);
        return response;
    }


}
