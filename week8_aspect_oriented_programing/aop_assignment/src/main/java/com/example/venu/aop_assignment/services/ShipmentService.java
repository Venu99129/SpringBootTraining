package com.example.venu.aop_assignment.services;

public interface ShipmentService {

    String orderPackage(Long orderId);
    String trackPackage(Long orderId);
}
