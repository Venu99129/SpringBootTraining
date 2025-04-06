package com.example.ecomerce.order_service.repositories;

import com.example.ecomerce.order_service.entites.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders , Long> {
}
