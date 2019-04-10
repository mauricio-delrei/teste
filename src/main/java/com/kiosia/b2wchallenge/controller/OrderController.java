package com.kiosia.b2wchallenge.controller;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.error.OutOfStockException;
import com.kiosia.b2wchallenge.mapper.OrderItemMapper;
import com.kiosia.b2wchallenge.mapper.OrderMapper;
import com.kiosia.b2wchallenge.model.Order;
import com.kiosia.b2wchallenge.service.OrderItemService;
import com.kiosia.b2wchallenge.service.OrderService;
import com.kiosia.b2wchallenge.service.ProductService;
import com.kiosia.b2wchallenge.vo.OrderItemVo;
import com.kiosia.b2wchallenge.vo.OrderVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
  public List<OrderVo> getOrders() {
    return orderService.findAll().stream().map(OrderMapper::domainToVo).collect(Collectors.toList());
  }

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
    final OrderVo orderVo = OrderMapper.domainToVo(orderService.findById(id));

    orderVo.setItems(orderItemService.findByOrderId(id).stream().map(OrderItemMapper::domainToVo).collect(Collectors.toList()));
    return orderVo;
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

  @PostMapping(value = "")
  @ApiOperation(
      value = "Create an Order with the data on the body",
      tags = {"Orders"})
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created"),
      @ApiResponse(code = 400, message = "Out of stock"),
      @ApiResponse(code = 404, message = "Not found")
  })
  synchronized public OrderVo postOrder (
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody OrderVo orderVo) throws NotFoundException, OutOfStockException {
    final List<OrderItemVo> items = orderVo.getItems();

    for (OrderItemVo item : items) {
      productService.checkQuantities(item);
      productService.decrementQuantities(item);
      orderItemService.fillPrices(item);
    }

    Order order = orderService.save(OrderMapper.voToDomain(orderVo));
    OrderVo response = OrderMapper.domainToVo(order);
    response.setItems(OrderItemMapper.domainListToVo(orderItemService.save(items, order)));
    return response;
  }
}
