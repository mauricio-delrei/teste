package com.kiosia.b2wchallenge.controller;

import com.kiosia.b2wchallenge.model.Product;
import com.kiosia.b2wchallenge.vo.ProductVo;

public class ProductMapper {
  public static ProductVo domainToVo(Product product) {
    //String name, String description, Integer stock, Double current_price
    final ProductVo productVo = new ProductVo(product.getName(), product.getDescription(), product.getStock(), product.getCurrentPrice());
    productVo.setId(product.getId());
    return productVo;
  }

  public static Product voToDomain(ProductVo productVo) {
    return new Product(productVo.getName(), productVo.getDescription(), productVo.getStock(), productVo.getCurrent_price());
  }
}
