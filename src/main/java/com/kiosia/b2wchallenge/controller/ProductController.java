package com.kiosia.b2wchallenge.controller;

import com.kiosia.b2wchallenge.error.NotFoundException;
import com.kiosia.b2wchallenge.mapper.ProductMapper;
import com.kiosia.b2wchallenge.model.Product;
import com.kiosia.b2wchallenge.service.ProductService;
import com.kiosia.b2wchallenge.vo.MultipleProductResponseVo;
import com.kiosia.b2wchallenge.vo.ProductResponseVo;
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
  public MultipleProductResponseVo getProducts() {
    final List<ProductVo> productVos = productService.findAll().stream().map(ProductMapper::domainToVo).collect(Collectors.toList());
    return new MultipleProductResponseVo(productVos, HttpStatus.OK);
  }


  @GetMapping(value = "/{product_id}")
  @ApiOperation(
      value = "Fetch a Product given its Id",
      tags = {"Products"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public ProductResponseVo getProduct(
      @NotNull @ApiParam(value = "Product Id")
      @PathVariable("product_id") Long id) {
    try {
      return new ProductResponseVo(ProductMapper.domainToVo(productService.findById(id)), HttpStatus.OK);
    } catch (NotFoundException e) {
      return new ProductResponseVo(HttpStatus.NOT_FOUND);
    }
  }

  @PatchMapping(value = "/{product_id}")
  @ApiOperation(
      value = "Patch a Product given its Id and the desired fields to be patched on the body",
      tags = {"Products"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public ProductResponseVo patchProduct(
      @NotNull @ApiParam(value = "Product Id")
      @PathVariable("product_id") final Long id,
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody ProductVo productVo) {
    final Product updatedProduct;
    try {
      updatedProduct = productService.patch(id, ProductMapper.voToDomain(productVo));
    } catch (NotFoundException e) {
      return new ProductResponseVo(HttpStatus.NOT_FOUND);
    }
    final ProductVo savedProduct = ProductMapper.domainToVo(updatedProduct);
    return new ProductResponseVo(savedProduct, HttpStatus.OK);
  }

  @PostMapping(value = "")
  @ApiOperation(
      value = "Create a Product with the data on the body",
      tags = {"Products"})
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created")
  })
  public ProductResponseVo postProduct(
      @NotNull @ApiParam(value = "Request body")
      @Validated @RequestBody ProductVo productVo) {
    Product product = ProductMapper.voToDomain(productVo);
    final ProductVo savedProduct = ProductMapper.domainToVo(productService.save(product));
    return new ProductResponseVo(savedProduct, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/{product_id}")
  @ApiOperation(
      value = "Delete a Product",
      tags = {"Products"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Not found")
  })
  public ProductResponseVo deleteProduct(
      @NotNull @ApiParam(value = "Product Id")
      @PathVariable("product_id") final Long id) {
    try {
      productService.delete(id);
    } catch (NotFoundException e) {
      return new ProductResponseVo(HttpStatus.NOT_FOUND);
    }
    return new ProductResponseVo(HttpStatus.OK);
  }
}
