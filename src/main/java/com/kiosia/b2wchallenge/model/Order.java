package com.kiosia.b2wchallenge.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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
    Optional.ofNullable(builder.id).ifPresent(this::setId);
    Optional.ofNullable(builder.date).ifPresent(this::setDate);
    Optional.ofNullable(builder.customer).ifPresent(this::setCustomer);
    Optional.ofNullable(builder.status).ifPresent(this::setStatus);
    Optional.ofNullable(builder.shipping).ifPresent(this::setShipping);
    Optional.ofNullable(builder.orderItemList).ifPresent(this::setOrderItemList);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Double getShipping() {
    return shipping;
  }

  public void setShipping(Double shipping) {
    this.shipping = shipping;
  }

  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  public void setOrderItemList(List<OrderItem> orderItemList) {
    this.orderItemList = orderItemList;
  }

  public void merge(Order order) {
    final Date date = order.getDate();
    final String customer = order.getCustomer();
    final String status = order.getStatus();
    final Double shipping = order.getShipping();
    final List<OrderItem> orderItemList = order.getOrderItemList();

    if(date != null) {
      this.date = date;
    }
    if(customer != null) {
      this.customer = customer;
    }
    if(status != null) {
      this.status = status;
    }
    if(shipping != null) {
      this.shipping = shipping;
    }
    if(orderItemList != null) {
      this.orderItemList = orderItemList;
    }
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
