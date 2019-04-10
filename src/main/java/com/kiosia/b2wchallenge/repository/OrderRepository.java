package com.kiosia.b2wchallenge.repository;

import com.kiosia.b2wchallenge.model.Order;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {
  Order save(Order order);

  Optional<Order> findById(Long id);

  List<Order> findAll();
}
