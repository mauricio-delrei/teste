package com.kiosia.b2wchallenge.vo;

import java.sql.Date;
import java.util.List;

public class OrderVo {

  private Date date;
  private String customer;
  private Double shipping;
  private List<OrderItemVo> items;
  private Long id;
  private String status;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public Double getShipping() {
    return shipping;
  }

  public void setShipping(Double shipping) {
    this.shipping = shipping;
  }

  public List<OrderItemVo> getItems() {
    return items;
  }

  public void setItems(List<OrderItemVo> items) {
    this.items = items;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void addItem(OrderItemVo orderItemVo) {
    this.items.add(orderItemVo);
  }

  @Override
  public String toString() {
    return "{\"id\": "+id+
        ", \"date\": "+date+
        ", \"customer\": \""+customer+
        "\", \"shipping\": "+shipping+
        ", \"status\": \""+status+
        "\", \"items\": "+items+"}";
  }
}
