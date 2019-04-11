package com.kiosia.b2wchallenge.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductResponseVo extends ResponseEntity<ProductVo> {
  public ProductResponseVo(HttpStatus status) {
    super(status);
  }

  public ProductResponseVo(ProductVo body, HttpStatus status) {
    super(body, status);
  }
}
