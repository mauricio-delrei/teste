package com.kiosia.b2wchallenge.vo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ReportResponseVo extends ResponseEntity<ReportVo> {
  public ReportResponseVo(ReportVo body, HttpStatus status) {
    super(body, status);
  }

  public ReportResponseVo(HttpStatus status) {
    super(status);
  }
}
