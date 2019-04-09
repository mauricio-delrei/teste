package com.kiosia.b2wchallenge.controller;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.mapper.OrderItemMapper;
import com.kiosia.b2wchallenge.mapper.OrderMapper;
import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.service.OrderService;
import com.kiosia.b2wchallenge.vo.OrderStatus;
import com.kiosia.b2wchallenge.vo.OrderVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Api(value = "SkyHyb Challenge API")
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping(value = "/{order_id}")
  @ApiOperation(
      value = "Fetch an Order given its Id",
      tags = {"Orders"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public OrderVo getOrder(
      @NotNull @ApiParam(value = "Order Id")
      @PathVariable("order_id") Long id) throws NotFoundException {
    return OrderMapper.domainToVo(orderService.findById(id));
  }

  @PatchMapping(value = "/{order_id}")
  @ApiOperation(
      value = "Patch an Order given its Id and the desired fields to be patched on the body",
      tags = {"Orders"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public OrderVo patchOrder(
      @NotNull @ApiParam(value = "Order Id")
      @PathVariable("order_id") final Long id,
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody OrderVo orderVo) {

    return OrderMapper.domainToVo(orderService.updateById(id, OrderMapper.voToDomain(orderVo)));
  }

  @PostMapping(value = "/")
  @ApiOperation(
      value = "Create an Order with the data on the body",
      tags = {"Orders"})
  @ResponseStatus(value = HttpStatus.CREATED)
  public OrderVo postOrder(
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody OrderVo orderVo) {
    Order order = new Order(orderVo.getDate(), orderVo.getCustomer(), OrderStatus.PENDING, orderVo.getShipping(), OrderItemMapper.voListToDomain(orderVo.getItems()));
    return OrderMapper.domainToVo(orderService.save(order));
  }
}
