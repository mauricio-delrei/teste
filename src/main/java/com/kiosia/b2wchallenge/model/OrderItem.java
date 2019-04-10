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
  @Column
  private Long productId;
  @Column
  private Integer quantity;
  @Column
  private Double price;

  public OrderItem() {
    // Do nothing
  }

  public OrderItem(Order order, Long productId, Integer quantity, Double price) {
    this.order = order;
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }

  private OrderItem(Builder builder) {
    id = builder.id;
    order = builder.order;
    productId = builder.productId;
    quantity = builder.quantity;
    price = builder.price;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Long getId() {
    return id;
  }

  public Order getOrder() {
    return order;
  }

  public Long getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setOrder(Order order) {
    this.order = order;
  }


  public static final class Builder {
    private Long id;
    private Order order;
    private Long productId;
    private Integer quantity;
    private Double price;

    private Builder() {
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withOrder(Order order) {
      this.order = order;
      return this;
    }

    public Builder withProductId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder withQuantity(Integer quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder withPrice(Double price) {
      this.price = price;
      return this;
    }

    public OrderItem build() {
      return new OrderItem(this);
    }
  }
}
