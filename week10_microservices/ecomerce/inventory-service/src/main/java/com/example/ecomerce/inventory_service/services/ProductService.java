package com.example.ecomerce.inventory_service.services;

import com.example.ecomerce.inventory_service.dtos.OrderRequestDto;
import com.example.ecomerce.inventory_service.dtos.OrderRequestItemDto;
import com.example.ecomerce.inventory_service.dtos.ProductDto;
import com.example.ecomerce.inventory_service.entities.Product;
import com.example.ecomerce.inventory_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllProducts(){
        log.info("fetching all inventory items......");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream().map(prod-> modelMapper.map(prod,ProductDto.class)).toList();
    }

    public ProductDto getProductById(long id){
        log.info("fetching product with id : {}",id);
        Optional<Product> inventory = productRepository.findById(id);

        return inventory.map(prod -> modelMapper.map(prod,ProductDto.class))
                .orElseThrow(()-> new RuntimeException("inventory not found with id : "+id));
    }

    @Transactional
    public Double reduceQuantityGetPrice(OrderRequestDto orderRequestDto) {
        log.info("reducing quantity calculating amount based on orderRequest.....");

        double totalPrice = 0.0;
        for (OrderRequestItemDto orders : orderRequestDto.getOrderItems()){
            Long productId = orders.getProductId();
            Integer quantity = orders.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(()-> new RuntimeException("products not found with product ID: "+productId));

            if(product.getStock() < quantity ){
                throw new RuntimeException("products cannot be fulfilled for given quantity");
            }
            product.setStock(product.getStock()-quantity);
            productRepository.save(product);
            totalPrice += product.getPrice()*quantity;
        }
        return totalPrice;
    }
}
