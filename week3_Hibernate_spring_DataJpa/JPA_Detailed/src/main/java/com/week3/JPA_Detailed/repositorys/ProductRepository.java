package com.week3.JPA_Detailed.repositorys;

import com.week3.JPA_Detailed.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByTittle(String pepsi);

    List<Product> findByCreatedAtAfter(LocalDateTime of);

    List<Product> findByQuantityAndPrice(int i, BigDecimal bigDecimal);

    List<Product> findByTittleLike(String tittle);

    List<Product> findByTittleContainingIgnoreCase(String tittle , Pageable pageable);

    // Optional<Product> findByTittleAndPrice(String pepsi, BigDecimal bigDecimal);


//    @Query("select e from Product e where e.tittle=?1 and e.price =?2")
//    Optional<Product> findByTittleAndPrice(String tittle, BigDecimal price);

    @Query("select e.tittle from Product e where e.tittle=:tittle and e.price=:price")
    Optional<Product> findByTittleAndPrice(String tittle, BigDecimal price);

    List<Product> findByOrderByTittle();

    List<Product> findByOrderByPrice();

    List<Product> findBy(Sort sortBy);
}
