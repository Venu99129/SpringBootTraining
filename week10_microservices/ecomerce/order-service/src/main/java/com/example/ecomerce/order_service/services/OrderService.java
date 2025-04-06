package com.example.ecomerce.order_service.services;

import com.example.ecomerce.order_service.dtos.OrderRequestDto;
import com.example.ecomerce.order_service.dtos.OrderRequestItemDto;
import com.example.ecomerce.order_service.entites.Orders;
import com.example.ecomerce.order_service.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;


    public List<OrderRequestDto> getAllOrders(){
        log.info("Fetching all orders");
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(order-> {
            OrderRequestDto dto = new OrderRequestDto();
            dto.setId(order.getId());
            dto.setTotalPrice(order.getTotalPrice());

            List<OrderRequestItemDto> items = order.getOrderItem().stream().map(item -> {
                OrderRequestItemDto itemDto = new OrderRequestItemDto();
                itemDto.setId(item.getId());
                itemDto.setProductId(item.getProductId());
                itemDto.setQuantity(item.getQuantity());
                return itemDto;
            }).collect(Collectors.toList());

            dto.setItems(items);
            return dto;
        }).toList();
    }

    public OrderRequestDto getOrderById(long id){
        log.info("Fetching order with ID: {}",id);
        Orders orders = ordersRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found with id: "+id));

        OrderRequestDto dto = new OrderRequestDto();
        dto.setId(orders.getId());
        dto.setTotalPrice(orders.getTotalPrice());

        List<OrderRequestItemDto> items = orders.getOrderItem().stream().map(item -> {
            OrderRequestItemDto itemDto = new OrderRequestItemDto();
            itemDto.setProductId(item.getProductId());
            itemDto.setQuantity(item.getQuantity());
            return itemDto;
        }).collect(Collectors.toList());

        dto.setItems(items);

        return dto;
    }
}
