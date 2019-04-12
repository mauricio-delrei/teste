package com.kiosia.b2wchallenge.repository;

import com.kiosia.b2wchallenge.model.OrderItem;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends Repository<OrderItem, Long> {
  OrderItem save(OrderItem orderItem);

  Optional<OrderItem> findById(Long id);

  List<OrderItem> findByOrderId(Long id);
}
