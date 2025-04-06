package com.example.ecomerce.order_service.repositories;

import com.example.ecomerce.order_service.entites.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
