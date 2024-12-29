package com.week3.JPA_Detailed.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products",
        uniqueConstraints ={
            @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}) ,
            @UniqueConstraint(name = "tittle_price_unique", columnNames = {"tittle_x", "price"})
        },
        indexes = {
            @Index(name = "sku_index" , columnList = "sku")
        }
)
public class Product {

    public Product(String tittle) {
        this.tittle = tittle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 20)
    private String sku;

    @Column(name = "tittle_x")
    private String tittle;

    @Column(precision = 6,scale = 2)
    private BigDecimal price;

    private Integer quantity;


    @CreationTimestamp
    private LocalDateTime createdAt;


    @UpdateTimestamp()
    private LocalDateTime updatedAt;
}
