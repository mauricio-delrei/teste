package com.kiosia.b2wchallenge.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MultipleProductResponseVo extends ResponseEntity<List<ProductVo>> {

  public MultipleProductResponseVo(HttpStatus status) {
    super(status);
  }

  public MultipleProductResponseVo(List<ProductVo> body, HttpStatus status) {
    super(body, status);
  }
}
