package com.kiosia.b2wchallenge.service;

import com.kiosia.b2wchallenge.mapper.OrderItemMapper;
import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.model.OrderItem;
import com.kiosia.b2wchallenge.repository.OrderItemRepository;
import com.kiosia.b2wchallenge.repository.ProductRepository;
import com.kiosia.b2wchallenge.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

  @Autowired
  OrderItemRepository orderItemRepository;

  @Autowired
  ProductRepository productRepository;

  public List<OrderItem> findByOrderId(Long id) {
    return orderItemRepository.findByOrderId(id);
  }

  public List<OrderItem> save(List<OrderItemVo> orderItemVo, Order order) {
    List<OrderItem> orderItems = orderItemVo.stream()
        .map(OrderItemMapper::voToDomain)
        .collect(Collectors.toList());
    orderItems.forEach(orderItem -> orderItem.setOrder(order));
    return orderItems.stream()
        .map(orderItemRepository::save)
        .collect(Collectors.toList());
  }

  public void fillPrices(OrderItemVo orderItemVo) {
    final Double currentPrice = productRepository.findById(orderItemVo.getProduct()).get().getCurrentPrice();
    orderItemVo.setPrice(currentPrice);
  }
}
