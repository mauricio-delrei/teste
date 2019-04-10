package com.kiosia.b2wchallenge.controller;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.mapper.ProductMapper;
import com.kiosia.b2wchallenge.model.Product;
import com.kiosia.b2wchallenge.service.ProductService;
import com.kiosia.b2wchallenge.vo.ProductVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "SkyHub Challenge API")
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping(value = "")
  @ApiOperation(
      value = "Fetch all Products",
      tags = {"Products"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation")
  })
  public List<ProductVo> getProducts() {
    return productService.findAll().stream().map(ProductMapper::domainToVo).collect(Collectors.toList());
  }


  @GetMapping(value = "/{product_id}")
  @ApiOperation(
      value = "Fetch a Product given its Id",
      tags = {"Products"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public ProductVo getProduct(
      @NotNull @ApiParam(value = "Product Id")
      @PathVariable("product_id") Long id) throws NotFoundException {
    return ProductMapper.domainToVo(productService.findById(id));
  }

  @PatchMapping(value = "/{product_id}")
  @ApiOperation(
      value = "Patch a Product given its Id and the desired fields to be patched on the body",
      tags = {"Products"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public ProductVo patchProduct(
      @NotNull @ApiParam(value = "Product Id")
      @PathVariable("product_id") final Long id,
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody ProductVo productVo) {

    return ProductMapper.domainToVo(productService.updateById(id, ProductMapper.voToDomain(productVo)));
  }

  @PostMapping(value = "")
  @ApiOperation(
      value = "Create a Product with the data on the body",
      tags = {"Products"})
  @ResponseStatus(value = HttpStatus.CREATED)
  public ProductVo postProduct(
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody ProductVo productVo) {
    Product product = ProductMapper.voToDomain(productVo);
    return ProductMapper.domainToVo(productService.save(product));
  }
}
