package com.kiosia.b2wchallenge.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  private Date date;
  @Column
  private String customer;
  @Column
  private String status;
  @Column
  private Double shipping;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
  private List<OrderItem> orderItemList;

  public Order() {
    // Do nothing
  }

  public Order(Date date, String customer, String status, Double shipping, List<OrderItem> orderItemList) {
    this.date = date;
    this.customer = customer;
    this.status = status;
    this.shipping = shipping;
    this.orderItemList = orderItemList;
  }

  private Order(Builder builder) {
    setId(builder.id);
    date = builder.date;
    customer = builder.customer;
    setStatus(builder.status);
    shipping = builder.shipping;
    orderItemList = builder.orderItemList;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Long getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public String getCustomer() {
    return customer;
  }

  public String getStatus() {
    return status;
  }

  public Double getShipping() {
    return shipping;
  }

  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public static final class Builder {
    private Long id;
    private Date date;
    private String customer;
    private String status;
    private Double shipping;
    private List<OrderItem> orderItemList;

    private Builder() {
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withDate(Date date) {
      this.date = date;
      return this;
    }

    public Builder withCustomer(String customer) {
      this.customer = customer;
      return this;
    }

    public Builder withStatus(String status) {
      this.status = status;
      return this;
    }

    public Builder withShipping(Double shipping) {
      this.shipping = shipping;
      return this;
    }

    public Builder withOrderItemList(List<OrderItem> orderItemList) {
      this.orderItemList = orderItemList;
      return this;
    }

    public Order build() {
      return new Order(this);
    }
  }
}
