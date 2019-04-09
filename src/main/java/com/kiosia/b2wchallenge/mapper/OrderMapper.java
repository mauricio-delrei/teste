package com.kiosia.b2wchallenge.mapper;

import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.vo.OrderVo;

public class OrderMapper {
  public static OrderVo domainToVo(Order order) {
    OrderVo vo = new OrderVo();
    vo.setId(order.getId());
    vo.setCustomer(order.getCustomer());
    vo.setDate(order.getDate());
    vo.setStatus(order.getStatus());
    //vo.setItems(order.getItems());
    vo.setShipping(order.getShipping());

    return vo;

//    return OrderVo.withId(order.getId())
//        .withDate(order.getDate())
//        .withCustomer(order.getCustomer())
//        .withStatus(order.getStatus())
//        .withShipping(order.getShipping())
//        .withItems(order.getItems())
//        .build();
  }

  public static Order voToDomain(OrderVo orderVo) {
    return new Order(orderVo.getDate(), orderVo.getCustomer(), orderVo.getStatus(), orderVo.getShipping(), OrderItemMapper.voListToDomain(orderVo.getItems()));
  }
}
