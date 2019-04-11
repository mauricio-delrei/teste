package com.kiosia.b2wchallenge.controller;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.error.OutOfStockException;
import com.kiosia.b2wchallenge.mapper.OrderItemMapper;
import com.kiosia.b2wchallenge.mapper.OrderMapper;
import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.service.OrderItemService;
import com.kiosia.b2wchallenge.service.OrderService;
import com.kiosia.b2wchallenge.service.ProductService;
import com.kiosia.b2wchallenge.vo.MultipleOrdersVo;
import com.kiosia.b2wchallenge.vo.OrderItemVo;
import com.kiosia.b2wchallenge.vo.OrderResponseVo;
import com.kiosia.b2wchallenge.vo.OrderVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "SkyHub Challenge API")
@RequestMapping("/order")
public class OrderController {
  @Autowired
  private OrderService orderService;
  @Autowired
  private ProductService productService;
  @Autowired
  private OrderItemService orderItemService;

  @GetMapping(value = "")
  @ApiOperation(
      value = "Fetch all Orders",
      tags = {"Orders"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation")
  })
  public MultipleOrdersVo getOrders() {
    final List<OrderVo> foundOrders = orderService.findAll().stream().map(OrderMapper::domainToVo).collect(Collectors.toList());
    return new MultipleOrdersVo(foundOrders, HttpStatus.OK);
  }

  @GetMapping(value = "/{order_id}")
  @ApiOperation(
      value = "Fetch an Order given its Id",
      tags = {"Orders"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public OrderResponseVo getOrder(
      @NotNull @ApiParam(value = "Order Id")
      @PathVariable("order_id") Long id) {
    final OrderVo orderVo;
    try {
      orderVo = OrderMapper.domainToVo(orderService.findById(id));
    } catch (NotFoundException e) {
      return new OrderResponseVo(HttpStatus.NOT_FOUND);
    }

    final List<OrderItemVo> items = orderItemService.findByOrderId(id).stream()
        .map(OrderItemMapper::domainToVo)
        .collect(Collectors.toList());
    orderVo.setItems(items);

    return new OrderResponseVo(orderVo, HttpStatus.OK);
  }

  @PatchMapping(value = "/{order_id}")
  @ApiOperation(
      value = "Patch an Order given its Id and the desired fields to be patched on the body",
      tags = {"Orders"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public OrderResponseVo patchOrder(
      @NotNull @ApiParam(value = "Order Id")
      @PathVariable("order_id") final Long id,
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody OrderVo orderVo) {

    final OrderVo savedOrder;
    try {
      savedOrder = OrderMapper.domainToVo(orderService.updateById(id, OrderMapper.voToDomain(orderVo)));
    } catch (ParseException e) {
      return new OrderResponseVo(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    return new OrderResponseVo(savedOrder, HttpStatus.OK);
  }

  @PostMapping(value = "")
  @ApiOperation(
      value = "Create an Order with the data on the body",
      tags = {"Orders"})
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created"),
      @ApiResponse(code = 400, message = "Out of stock"),
      @ApiResponse(code = 404, message = "Not found")
  })
  synchronized public OrderResponseVo postOrder (
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody OrderVo orderVo) {
    final List<OrderItemVo> items = orderVo.getItems();

    for (OrderItemVo item : items) {
      try {
        productService.checkQuantities(item);
      } catch (OutOfStockException err) {
        return new OrderResponseVo(HttpStatus.PRECONDITION_FAILED);
      } catch (NotFoundException e) {
        return new OrderResponseVo(HttpStatus.NOT_FOUND);
      }
      productService.decrementQuantities(item);
      orderItemService.fillPrices(item);
    }

    Order order;
    try {
      order = orderService.save(OrderMapper.voToDomain(orderVo));
    } catch (ParseException e) {
      return new OrderResponseVo(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    OrderVo response = OrderMapper.domainToVo(order);
    response.setItems(OrderItemMapper.domainListToVo(orderItemService.save(items, order)));
    return new OrderResponseVo(response, HttpStatus.CREATED);
  }
}
