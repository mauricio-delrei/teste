package com.kiosia.b2wchallenge.service;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.model.Product;
import com.kiosia.b2wchallenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Product findById(Long id) throws NotFoundException {
    Optional<Product> product = productRepository.findById(id);
    if(product.isPresent()) {
      return product.get();
    }
    throw new NotFoundException("Order with id "+id+" was not found");
  }

  public Product updateById(Long id, Product product) {
    product.setId(id);
    return productRepository.save(product);
  }
}
