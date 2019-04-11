package com.kiosia.b2wchallenge.vo;

import java.util.List;

public class OrderVo {

  private String date;
  private String customer;
  private Double shipping;
  private List<OrderItemVo> items;
  private Long id;
  private String status;

  public OrderVo() {
    // Do nothing
  }

  private OrderVo(Builder builder) {
    setDate(builder.date);
    setCustomer(builder.customer);
    setShipping(builder.shipping);
    setItems(builder.items);
    setId(builder.id);
    setStatus(builder.status);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
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

  public static final class Builder {
    private String date;
    private String customer;
    private Double shipping;
    private List<OrderItemVo> items;
    private Long id;
    private String status;

    private Builder() {
    }

    public Builder withDate(String date) {
      this.date = date;
      return this;
    }

    public Builder withCustomer(String customer) {
      this.customer = customer;
      return this;
    }

    public Builder withShipping(Double shipping) {
      this.shipping = shipping;
      return this;
    }

    public Builder withItems(List<OrderItemVo> items) {
      this.items = items;
      return this;
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withStatus(String status) {
      this.status = status;
      return this;
    }

    public OrderVo build() {
      return new OrderVo(this);
    }
  }

  @Override
  public String toString() {
    return "OrderVo{" +
        "date=" + date +
        ", customer='" + customer + '\'' +
        ", shipping=" + shipping +
        ", items=" + items +
        ", id=" + id +
        ", status='" + status + '\'' +
        '}';
  }
}
