package com.kiosia.b2wchallenge.repository;

import com.kiosia.b2wchallenge.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {
  Product save(Product product);

  Optional<Product> findById(Long id);

  List<Product> findAll();

  @Transactional
  @Modifying
  @Query("UPDATE Product SET stock = stock - :newStock WHERE id = :productId")
  void decrementStock(@Param("productId") Long productId, @Param("newStock") Integer desiredQtt);
}