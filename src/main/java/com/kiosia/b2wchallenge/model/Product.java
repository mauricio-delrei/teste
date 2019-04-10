package com.kiosia.b2wchallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  private String name;
  @Column
  private String description;
  @Column
  private Integer stock;
  @Column
  private Double current_price;

  public Product() {
    // Do nothing
  }

  public Product(String name, String description, Integer stock, Double current_price) {
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.current_price = current_price;
  }

  private Product(Builder builder) {
    setId(builder.id);
    name = builder.name;
    description = builder.description;
    stock = builder.stock;
    current_price = builder.current_price;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Integer getStock() {
    return stock;
  }

  public Double getCurrentPrice() {
    return current_price;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public static final class Builder {
    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double current_price;

    private Builder() {
    }

    public Builder withId(Long id) {
      this.id = id;
      return this;
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder withStock(Integer stock) {
      this.stock = stock;
      return this;
    }

    public Builder withCurrent_price(Double current_price) {
      this.current_price = current_price;
      return this;
    }

    public Product build() {
      return new Product(this);
    }
  }
}