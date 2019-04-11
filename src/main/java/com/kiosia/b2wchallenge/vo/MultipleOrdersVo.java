package com.kiosia.b2wchallenge.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MultipleOrdersVo extends ResponseEntity<List<OrderVo>> {
  public MultipleOrdersVo(HttpStatus status) {
    super(status);
  }

  public MultipleOrdersVo(List<OrderVo> body, HttpStatus status) {
    super(body, status);
  }
}
