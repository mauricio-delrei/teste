package com.kiosia.b2wchallenge.mapper;

import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.vo.OrderVo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderMapper {
  private static final String DATE_PATTERN = "yyyy-MM-dd";

  public static OrderVo domainToVo(Order order) {
    DateFormat fmt = new SimpleDateFormat(DATE_PATTERN);

    return OrderVo.newBuilder()
        .withId(order.getId())
        .withCustomer(order.getCustomer())
        .withDate(fmt.format(order.getDate()))
        .withStatus(order.getStatus())
        .withShipping(order.getShipping())
        .withTotal(order.getTotal())
        .build();
  }

  public static Order voToDomain(OrderVo orderVo) throws ParseException {
    final String dateString = orderVo.getDate();
    Date date = null;
    if(dateString != null) {
      DateFormat fmt = new SimpleDateFormat(DATE_PATTERN);
      date = new Date(fmt.parse(dateString).getTime());
    }

    return Order.newBuilder()
        .withDate(date)
        .withCustomer(orderVo.getCustomer())
        .withStatus(orderVo.getStatus())
        .withShipping(orderVo.getShipping())
        .withTotal(orderVo.getTotal())
        .build();
  }
}
