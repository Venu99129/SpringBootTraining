package com.example.ecomerce.order_service.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private List<OrderRequestItemDto> orderItems;
    private Double totalPrice;
}
