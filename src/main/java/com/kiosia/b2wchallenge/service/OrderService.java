package com.kiosia.b2wchallenge.service;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.repository.OrderItemRepository;
import com.kiosia.b2wchallenge.repository.OrderRepository;
import com.kiosia.b2wchallenge.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderItemRepository orderItemRepository;

  public Order save(Order order) {
    order.setStatus(OrderStatus.PENDING);
    return orderRepository.save(order);
  }

  public Order findById(Long id) throws NotFoundException {
    Optional<Order> order = orderRepository.findById(id);
    if(order.isPresent()) {
      return order.get();
    }
    throw new NotFoundException("Order with id "+id+" was not found");
  }

  public Order updateById(Long id, Order order) {
    order.setId(id);
    return orderRepository.save(order);
  }

  public List<Order> findAll() {
    return orderRepository.findAll();
  }
}
