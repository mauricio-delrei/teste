package com.kiosia.b2wchallenge.repository;

import com.kiosia.b2wchallenge.model.Product;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {
  Product save(Product product);

  Optional<Product> findById(Long id);
}