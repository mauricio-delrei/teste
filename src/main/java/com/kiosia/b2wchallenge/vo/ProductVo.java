package com.kiosia.b2wchallenge.vo;

public class ProductVo {

  private Long id;
  private final String name;
  private final String description;
  private final Integer stock;
  private final Double current_price;

  public ProductVo(String name, String description, Integer stock, Double current_price) {
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.current_price = current_price;
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

  public Double getCurrent_price() {
    return current_price;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
