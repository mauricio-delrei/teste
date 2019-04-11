package com.kiosia.b2wchallenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductVo {

  private Long id;
  private String name;
  private String description;
  private Integer stock;
  @JsonProperty(value = "current_price")
  private Double currentPrice;

  public ProductVo() {
    // Do nothing
  }

  public ProductVo(String name, String description, Integer stock, Double currentPrice) {
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.currentPrice = currentPrice;
  }

  private ProductVo(Builder builder) {
    setId(builder.id);
    name = builder.name;
    description = builder.description;
    stock = builder.stock;
    currentPrice = builder.currentPrice;
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

    public ProductVo build() {
      return new ProductVo(this);
    }
  }
}
