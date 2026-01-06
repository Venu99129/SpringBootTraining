package com.example.ecomerce.order_service.controllers;

import com.example.ecomerce.order_service.config.FeaturesEnabledConfig;
import com.example.ecomerce.order_service.dtos.OrderRequestDto;
import com.example.ecomerce.order_service.dtos.OrderRequestItemDto;
import com.example.ecomerce.order_service.dtos.ValidUserResponseDto;
import com.example.ecomerce.order_service.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
@RefreshScope
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @Value("${my.veriable}")
    private String veriable;

    private final FeaturesEnabledConfig featuresEnabledConfig;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) throws JsonProcessingException {
//        String userInfo = request.getHeader("CurrentUser");
//        ValidUserResponseDto userDto = objectMapper.readValue(userInfo, ValidUserResponseDto.class);
        //return userDto.toString()+"\nHello from orders \n"+veriable;

        if(featuresEnabledConfig.isUserTrackingEnabled())
            return "\nHello from orders your user is : enabled awwwww \n"+veriable;
        else
            return "\nHello from orders your user is : disabled woohhh \n"+veriable;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequestDto orderRequestDto1 = orderService.createOrder(orderRequestDto);
        return new ResponseEntity<>(orderRequestDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(){
        List<OrderRequestDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id){
        OrderRequestDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
