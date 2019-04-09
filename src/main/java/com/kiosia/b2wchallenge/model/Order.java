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
}
