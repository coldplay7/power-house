package com.powerplant.service;

import com.powerplant.mapper.BatteryMapper;
import com.powerplant.mapper.BatteryMapperImpl;
import com.powerplant.repository.BatteryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.stream.Collectors;

import static com.powerplant.helper.BatteryHelper.batteries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class BatteryServiceTest {

    @Mock
    private BatteryRepository repository;
    private BatteryService service;
    private BatteryMapper mapper;
    @BeforeEach
    void setUp() {
        mapper = new BatteryMapperImpl();
        openMocks(this);
        service = new BatteryServiceImpl(repository, mapper);
    }

    @Test
    void givenListOfBatteries_whenSave_shouldInvokeRepository() {
        var batteries = batteries();
        service.save(batteries);
        verify(repository, times(1)).saveAll(batteries.stream()
                .map(mapper::entity)
                .collect(Collectors.toList()));
    }

    @Test
    void givenPostCodeRange_whenList_thenShouldReturnSortedBatteryList() {
        when(repository.findAllByPostCodeBetween(1, 4))
                .thenReturn(batteries().stream().map(mapper::entity).collect(Collectors.toList()));
        var response = service.list(1, 4);

        assertNotNull(response);
        assertEquals(4, response.getBatteries().size());
        assertEquals("battery a", response.getBatteries().get(0).getName());
        assertEquals("battery b", response.getBatteries().get(1).getName());
        assertEquals("battery c", response.getBatteries().get(2).getName());
        assertEquals("battery d", response.getBatteries().get(3).getName());
        assertEquals(110, response.getAvgWattCapacity());
        assertEquals(440, response.getTotalWattCapacity());

    }
}