package com.week3.JPA_Detailed;

import com.week3.JPA_Detailed.entities.Product;
import com.week3.JPA_Detailed.repositorys.ProductRepository;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaDetailedApplicationTests {

//	@Autowired
//	ProductRepository productRepository;
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void testProductRepository(){
//		Product productEntity = Product.builder()
//				.sku("coca12").price(BigDecimal.valueOf(12.3)).tittle("coca chocolate").quantity(12)
//				.build();
//
//		Product savedEntity = productRepository.save(productEntity);
//
//		System.out.println(savedEntity);
//	}
//
//	@Test
//	@After("testProductRepository")
//	void testGetRepositoryValues(){
//
////		List<Product> products = productRepository.findByTittle("Pepsi");
////		System.out.println(products);
////
////		List<Product> afterDateProducts = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1,0,0,0));
////
////		System.out.println(afterDateProducts);
////
////		List<Product> productList = productRepository.findByQuantityAndPrice(2, BigDecimal.valueOf(24.4));
////
////		System.out.println(productList);
//
//		List<Product> productList = productRepository.findByTittleContainingIgnoreCase("Co");
//		System.out.println(productList);
//	}
//
//	@Test
//	@After("testProductRepository")
//	void getSingleUserFromRepository(){
//		Optional<Product> product = productRepository.findByTittleAndPrice("pepsi" , BigDecimal.valueOf(14.4));
//		product.ifPresent(prd -> System.out.println(prd.toString()));
//	}
//
//	@Test
//	void findAllByOrder(){
//		List<Product> productList = productRepository.findByOrderByTittle();
//		System.out.println(productList);
//	}
//
//

}
