package com.example.venu.aopapp.services;

public interface ShipmentService {

    String orderPackage(Long orderId);
    String trackPackage(Long orderId);
}
