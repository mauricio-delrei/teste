package com.kiosia.b2wchallenge.service;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.error.OutOfStockException;
import com.kiosia.b2wchallenge.model.Product;
import com.kiosia.b2wchallenge.repository.ProductRepository;
import com.kiosia.b2wchallenge.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    throw new NotFoundException("Product with id "+id+" was not found");
  }

  public Product patch(Long id, Product product) throws NotFoundException {
    Product p = productRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Product with id "+id+" was not found"));
    p.merge(product);
    return productRepository.save(p);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public void checkQuantities(OrderItemVo orderItemVo) throws OutOfStockException, NotFoundException {
    final Integer desiredQtt = orderItemVo.getQuantity();
    final Long productId = orderItemVo.getProduct();
    final Optional<Product> product = productRepository.findById(productId);
    if(product.isPresent()) {
      final Integer stock = product.get().getStock();
      if(stock<desiredQtt)
        throw new OutOfStockException("Product with id " + productId + " has not enough stock for this order. Only " + stock + " items are available.");
    } else {
      throw new NotFoundException("Product with id " + productId + " was not found");
    }
  }

  public void decrementQuantities(OrderItemVo orderItemVo) {
    final Integer desiredQtt = orderItemVo.getQuantity();
    final Long productId = orderItemVo.getProduct();

    productRepository.decrementStock(productId, desiredQtt);
  }
}
