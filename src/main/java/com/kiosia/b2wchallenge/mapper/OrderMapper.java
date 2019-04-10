package com.kiosia.b2wchallenge.mapper;

import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.vo.OrderVo;

public class OrderMapper {
  public static OrderVo domainToVo(Order order) {
    return OrderVo.newBuilder()
        .withId(order.getId())
        .withCustomer(order.getCustomer())
        .withDate(order.getDate())
        .withStatus(order.getStatus())
        .withShipping(order.getShipping())
        .build();
  }

  public static Order voToDomain(OrderVo orderVo) {
    return Order.newBuilder()
        .withDate(orderVo.getDate())
        .withCustomer(orderVo.getCustomer())
        .withStatus(orderVo.getStatus())
        .withShipping(orderVo.getShipping())
        .build();
  }
}
