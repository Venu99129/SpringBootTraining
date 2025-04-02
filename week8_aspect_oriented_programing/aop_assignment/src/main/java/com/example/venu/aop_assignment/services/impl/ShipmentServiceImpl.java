package com.example.venu.aop_assignment.services.impl;

import com.example.venu.aop_assignment.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    @Override
    public String orderPackage(Long orderId) {

        try {
            log.info("processing the order");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Error occurred while processing the order ",e);
            throw new RuntimeException(e);
        }
        return "Order has been processed successfully, orderId: "+orderId;
    }

    @Override
    @Transactional
    public String trackPackage(Long orderId) {

        try {
            log.info("tracking the order.......");
            Thread.sleep(500);

            throw new RuntimeException("exception occurred during trackPackage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
