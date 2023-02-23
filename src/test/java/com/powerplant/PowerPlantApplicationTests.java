package com.powerplant;

import com.powerplant.controller.BatteryController;
import com.powerplant.helper.BatteryHelper;
import com.powerplant.repository.BatteryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PowerPlantApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private BatteryController controller;
    @Autowired
    private BatteryRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void integrationTest() {
        controller.save(BatteryHelper.batteries());
        assertEquals(4, repository.findAll().size());
        assertEquals(3, repository.findAllByPostCodeBetween(1, 3).size());

        var response = controller.list(1, 3);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getBatteries());
        assertEquals(3, response.getBody().getBatteries().size());
    }

}
