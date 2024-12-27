package com.week3.JPA_Detailed.controllers;

import com.week3.JPA_Detailed.entities.Product;
import com.week3.JPA_Detailed.repositorys.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Integer PAGE_SIZE = 10;
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(defaultValue = "") String tittle,
                                     @RequestParam( defaultValue = "id") String sortBy ,
                                     @RequestParam(defaultValue = "0") Integer pageNumber){
//
//        ** sorting using sort class**

//        return productRepository.findBy(
//                Sort.by(Sort.Order.desc(sortBy),
//                Sort.Order.asc("tittle")));

        // *** finding using pageable class*****

//        Pageable pageable = PageRequest.of(pageNumber , PAGE_SIZE , Sort.by(sortBy));

        // ***  with findAll method**
//        return productRepository.findAll(pageable).getContent();

        // ** with normal findBy methods *****

        return productRepository.findByTittleContainingIgnoreCase(tittle ,
                PageRequest.of(pageNumber , PAGE_SIZE));

    }


}
