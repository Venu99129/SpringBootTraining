package com.example.ecomerce.inventory_service.dtos;

import lombok.Data;

@Data
public class ProductDto {

    private long id;
    private String tittle;
    private double price;
    private int stock;
}
