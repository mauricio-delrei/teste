package com.kiosia.b2wchallenge.service;

import com.kiosia.b2wchallenge.repository.OrderRepository;
import com.kiosia.b2wchallenge.vo.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ReportService {
  private static final String DATE_PATTERN = "yyyy-MM-dd";

  @Autowired
  OrderRepository orderRepository;

  public ReportVo generateByDate(String initialDateString, String finalDateString) throws ParseException {

    DateFormat fmt = new SimpleDateFormat(DATE_PATTERN);
    Date initialDate = new Date(fmt.parse(initialDateString).getTime());
    Date finalDate = new Date(fmt.parse(finalDateString).getTime());
    Double atp = orderRepository.findAtpByDatePeriod(initialDate, finalDate);
    if(atp == null) {
      atp = 0D;
    }

    return ReportVo.newBuilder()
        .withInitialDate(initialDateString)
        .withFinalDate(finalDateString)
        .withAtp(atp)
        .build();
  }
}
