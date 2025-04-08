package com.example.ecomerce.order_service.clients;

import com.example.ecomerce.order_service.dtos.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "inventory-service" ,path="/inventory" )
public interface InventoryFeignClient {

    @PostMapping("/products/reduceQuantity")
    Double reduceQuantity(OrderRequestDto orderRequestDto);
}
