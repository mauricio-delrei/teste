package com.kiosia.b2wchallenge.mapper;

import com.kiosia.b2wchallenge.model.OrderItem;
import com.kiosia.b2wchallenge.vo.OrderItemVo;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {
  public static List<OrderItem> voListToDomain(List<OrderItemVo> items) {
    return items.stream().map(OrderItemMapper::voToDomain).collect(Collectors.toList());
  }

  public static OrderItem voToDomain(OrderItemVo orderItemVo) {
    return OrderItem.newBuilder()
        .withId(orderItemVo.getId())
        .withPrice(orderItemVo.getPrice())
        .withQuantity(orderItemVo.getQuantity())
        .withProductId(orderItemVo.getProduct())
        .build();
  }

  public static OrderItemVo domainToVo(OrderItem orderItem) {
    return OrderItemVo.newBuilder()
        .withId(orderItem.getId())
        .withPrice(orderItem.getPrice())
        .withQuantity(orderItem.getQuantity())
        .build();
  }

  public static List<OrderItemVo> domainListToVo(List<OrderItem> orderItems) {
    return orderItems.stream().map(OrderItemMapper::domainToVo).collect(Collectors.toList());
  }
}
