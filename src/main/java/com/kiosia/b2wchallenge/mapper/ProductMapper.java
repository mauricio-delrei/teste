package com.kiosia.b2wchallenge.mapper;

import com.kiosia.b2wchallenge.model.Product;
import com.kiosia.b2wchallenge.vo.ProductVo;

public class ProductMapper {
  public static ProductVo domainToVo(Product product) {
    return ProductVo.newBuilder()
        .withDescription(product.getDescription())
        .withId(product.getId())
        .withName(product.getName())
        .withStock(product.getStock())
        .withCurrentPrice(product.getCurrentPrice())
        .build();
  }

  public static Product voToDomain(ProductVo productVo) {
    return Product.newBuilder()
        .withDescription(productVo.getDescription())
        .withId(productVo.getId())
        .withName(productVo.getName())
        .withStock(productVo.getStock())
        .withCurrentPrice(productVo.getCurrentPrice())
        .build();
  }
}
