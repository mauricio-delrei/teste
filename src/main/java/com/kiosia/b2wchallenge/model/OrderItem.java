package com.kiosia.b2wchallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  @Column(name = "product_id")
  private Long product_id;
  @Column
  private Integer quantity;
  @Column
  private Double price;

  public OrderItem() {
    // Do nothing
  }

  public OrderItem(Order order, Long product_id, Integer quantity, Double price) {
    this.order = order;
    this.product_id = product_id;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public Order getOrder() {
    return order;
  }

  public Long getProduct_id() {
    return product_id;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Double getPrice() {
    return price;
  }
}
