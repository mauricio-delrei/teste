package com.kiosia.b2wchallenge.mapper;

import com.kiosia.b2wchallenge.model.OrderItem;
import com.kiosia.b2wchallenge.vo.OrderItemVo;

public class ItemListMapper {
  public static OrderItemVo voToDomain(OrderItem orderItem) {
    return OrderItemVo.newBuilder()
        .withQuantity(orderItem.getQuantity())
        .withPrice(orderItem.getPrice())
        .withProduct(orderItem.getProductId())
        .withId(orderItem.getId())
        .build();
  }
}
