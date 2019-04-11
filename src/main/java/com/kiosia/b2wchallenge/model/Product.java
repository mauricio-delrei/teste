package com.kiosia.b2wchallenge.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String name;
  @Column
  private String description;
  @Column
  private Integer stock;
  @Column
  private Double currentPrice;

  public Product() {
    // Do nothing
  }

  public Product(String name, String description, Integer stock, Double currentPrice) {
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.currentPrice = currentPrice;
  }

  private Product(Builder builder) {
    Optional.ofNullable(builder.id).ifPresent(this::setId);
    Optional.ofNullable(builder.name).ifPresent(this::setName);
    Optional.ofNullable(builder.description).ifPresent(this::setDescription);
    Optional.ofNullable(builder.stock).ifPresent(this::setStock);
    Optional.ofNullable(builder.currentPrice).ifPresent(this::setCurrentPrice);
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Double getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(Double currentPrice) {
    this.currentPrice = currentPrice;
  }

  public void merge(Product product) {
    final Integer stock = product.getStock();
    final Double currentPrice = product.getCurrentPrice();
    final String description = product.getDescription();
    final String name = product.getName();

    if(stock != null) {
      this.stock = stock;
    }
    if(currentPrice != null) {
      this.currentPrice = currentPrice;
    }
    if(description != null) {
      this.description = description;
    }
    if(name != null) {
      this.name = name;
    }
  }

  public static final class Builder {
    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double currentPrice;

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

    public Builder withCurrentPrice(Double currentPrice) {
      this.currentPrice = currentPrice;
      return this;
    }

    public Product build() {
      return new Product(this);
    }
  }
}
