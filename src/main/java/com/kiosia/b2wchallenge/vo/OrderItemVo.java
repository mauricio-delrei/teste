package com.kiosia.b2wchallenge.vo;

public class OrderItemVo {
  private Long id;
  private Long product;
  private Integer quantity;
  private Double price;

  public OrderItemVo() {
    // Do nothing
  }

  public OrderItemVo(Long id, Long product, Integer quantity, Double price) {
    this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.product = product;
  }

  private OrderItemVo(Builder builder) {
    id = builder.id;
    product = builder.product;
    quantity = builder.quantity;
    price = builder.price;
  }

  public static Builder newBuilder() {
    return new Builder();
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

  public Long getProduct() {
    return product;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  public static final class Builder {
    private Long id;
    private Long product;
    private Integer quantity;
    private Double price;

    private Builder() {
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withProduct(Long product) {
      this.product = product;
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

    public OrderItemVo build() {
      return new OrderItemVo(this);
    }
  }

  @Override
  public String toString() {
    return "OrderItemVo{" +
        "id=" + id +
        ", product=" + product +
        ", quantity=" + quantity +
        ", price=" + price +
        '}';
  }
}
