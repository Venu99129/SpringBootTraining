package com.example.venu.aopapp.services.impl;

import com.example.venu.aopapp.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ShipmentServiceImplTest {

    @Autowired
    private ShipmentService shipmentService;

    @Test
    public void testOrderPackage(){
        String returned  = shipmentService.orderPackage(4L);
        log.info(returned);
    }

    @Test
    public void testTrackOrder(){
        shipmentService.trackPackage(4L);
    }
}