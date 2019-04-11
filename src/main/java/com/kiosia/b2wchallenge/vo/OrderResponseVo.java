package com.kiosia.b2wchallenge.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OrderResponseVo extends ResponseEntity<OrderVo> {
  public OrderResponseVo(HttpStatus status) {
    super(status);
  }

  public OrderResponseVo(OrderVo body, HttpStatus status) {
    super(body, status);
  }
}
