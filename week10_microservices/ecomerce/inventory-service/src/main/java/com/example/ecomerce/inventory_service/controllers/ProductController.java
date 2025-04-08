package com.example.ecomerce.inventory_service.controllers;

import com.example.ecomerce.inventory_service.clients.OrderFeignClient;
import com.example.ecomerce.inventory_service.dtos.OrderRequestDto;
import com.example.ecomerce.inventory_service.dtos.ProductDto;
import com.example.ecomerce.inventory_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

//    private final DiscoveryClient discoveryClient;
//    private final RestClient restClient;

    private final OrderFeignClient orderFeignClient;

    @GetMapping("/fetchingOrders")
    public String fetchingOrders(){
//        ServiceInstance instance = discoveryClient.getInstances("order-service").getFirst();
//        return restClient.get()
//                .uri(instance.getUri()+"/orders/core/hello")
//                .retrieve()
//                .body(String.class);

        return orderFeignClient.hello();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory(){
        List<ProductDto> inventories = productService.getAllProducts();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/reduceQuantity")
    public ResponseEntity<Double> reduceQuantity(@RequestBody OrderRequestDto orderRequestDto){
        Double price = productService.reduceQuantityGetPrice(orderRequestDto);
        return ResponseEntity.ok(price);
    }

}
