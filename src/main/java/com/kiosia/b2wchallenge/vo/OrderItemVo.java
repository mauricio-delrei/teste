package com.kiosia.b2wchallenge.vo;

public class OrderItemVo {
  private final Long id;
  private final Integer quantity;
  private final Double price;

  public OrderItemVo(Long id, Integer quantity, Double price) {
    this.id = id;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Double getPrice() {
    return price;
  }
}
