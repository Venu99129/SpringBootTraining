package com.example.ecomerce.inventory_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service", path="/orders")
public interface OrderFeignClient {

    @GetMapping("/core/hello")
    String hello();
}
