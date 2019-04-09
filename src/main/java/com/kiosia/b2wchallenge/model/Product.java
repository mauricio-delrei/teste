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
}
