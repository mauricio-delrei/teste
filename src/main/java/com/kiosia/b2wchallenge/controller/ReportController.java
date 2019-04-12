package com.kiosia.b2wchallenge.controller;

import com.kiosia.b2wchallenge.service.ReportService;
import com.kiosia.b2wchallenge.vo.ReportResponseVo;
import com.kiosia.b2wchallenge.vo.ReportVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@Api(value = "SkyHub Challenge API")
@RequestMapping("/report")
public class ReportController {

  @Autowired
  private ReportService reportService;

  @GetMapping(value = "")
  @ApiOperation(
      value = "Generates a Report given initial and end date",
      tags = {"Reports"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation")
  })
  public ReportResponseVo getReport(
      @ApiParam(value = "initial date")
      @RequestParam(value = "start") String initialDate,
      @ApiParam(value = "final date")
      @RequestParam(value = "end") String finalDate
  ) {
    ReportVo report = null;
    try {
      report = reportService.generateByDate(initialDate, finalDate);
    } catch (ParseException e) {
      return new ReportResponseVo(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    return new ReportResponseVo(report, HttpStatus.OK);
  }
}
