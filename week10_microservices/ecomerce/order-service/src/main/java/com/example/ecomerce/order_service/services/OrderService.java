package com.example.ecomerce.order_service.services;

import com.example.ecomerce.order_service.clients.InventoryFeignClient;
import com.example.ecomerce.order_service.dtos.OrderRequestDto;
import com.example.ecomerce.order_service.dtos.OrderRequestItemDto;
import com.example.ecomerce.order_service.entites.OrderItem;
import com.example.ecomerce.order_service.entites.OrderStatus;
import com.example.ecomerce.order_service.entites.Orders;
import com.example.ecomerce.order_service.repositories.OrdersRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;
    private final InventoryFeignClient inventoryFeignClient;

    public List<OrderRequestDto> getAllOrders(){
        log.info("Fetching all orders");
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(order-> {
            OrderRequestDto dto = new OrderRequestDto();
            dto.setId(order.getId());
            dto.setTotalPrice(order.getTotalPrice());

            List<OrderRequestItemDto> items = order.getOrderItems().stream().map(item -> {
                OrderRequestItemDto itemDto = new OrderRequestItemDto();
                itemDto.setId(item.getId());
                itemDto.setProductId(item.getProductId());
                itemDto.setQuantity(item.getQuantity());
                return itemDto;
            }).collect(Collectors.toList());

            dto.setOrderItems(items);
            return dto;
        }).toList();
    }

    public OrderRequestDto getOrderById(long id){
        log.info("Fetching order with ID: {}",id);
        Orders orders = ordersRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found with id: "+id));

        OrderRequestDto dto = new OrderRequestDto();
        dto.setId(orders.getId());
        dto.setTotalPrice(orders.getTotalPrice());

        List<OrderRequestItemDto> items = orders.getOrderItems().stream().map(item -> {
            OrderRequestItemDto itemDto = new OrderRequestItemDto();
            itemDto.setProductId(item.getProductId());
            itemDto.setQuantity(item.getQuantity());
            return itemDto;
        }).collect(Collectors.toList());

        dto.setOrderItems(items);

        return dto;
    }

    @Transactional
//    @Retry(name = "inventoryRetry" , fallbackMethod = "createOrderFallBackMethod")
    @RateLimiter(name = "inventoryRateLimiter", fallbackMethod = "createOrderRateLimiterFallBackMethod")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "createOrderFallBackMethod")
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        log.info("creating the orderItem and order with given orderRequestDto.......");
        double ordersPrice = inventoryFeignClient.reduceQuantity(orderRequestDto);

        Orders orders = modelMapper.map(orderRequestDto,Orders.class);
        for (OrderItem orderItem : orders.getOrderItems()){
            orderItem.setOrder(orders);
        }
        orders.setTotalPrice(ordersPrice);
        orders.setOrderStatus(OrderStatus.CONFIRMED);

        Orders savedOrder = ordersRepository.save(orders);

        return modelMapper.map(savedOrder , OrderRequestDto.class);
    }

    public OrderRequestDto createOrderFallBackMethod(OrderRequestDto orderRequestDto , Throwable throwable){
        log.error("Fallback occurred due to : {}",throwable.getLocalizedMessage());
        return new OrderRequestDto();
    }
    public OrderRequestDto createOrderRateLimiterFallBackMethod(OrderRequestDto orderRequestDto , Throwable throwable){
        log.error("Fallback occurred due rate limiter to : {}",throwable.getLocalizedMessage());
        return new OrderRequestDto();
    }
}
