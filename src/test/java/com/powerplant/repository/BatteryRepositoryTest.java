package com.powerplant.repository;

import com.powerplant.entity.BatteryEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BatteryRepositoryTest {

    @Autowired
    private BatteryRepository repository;

    @BeforeEach
    void setUp() {
        repository.saveAll(batteries());
    }

    @Test
    void findAllByPostCodeBetween() {
        assertEquals(3, repository.findAllByPostCodeBetween(1, 3).size());
    }

    private List<BatteryEntity> batteries() {
        return of(entity("battery1", 1),
                entity("battery2", 2),
                entity("battery3", 3),
                entity("battery4", 4));
    }

    private BatteryEntity entity(String name, Integer postCode) {
        var e = new BatteryEntity();
        e.setName(name);
        e.setPostCode(postCode);
        e.setWattCapacity(100);
        return e;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

}