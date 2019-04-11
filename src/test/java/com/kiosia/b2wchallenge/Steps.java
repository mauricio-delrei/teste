package com.kiosia.b2wchallenge;

import com.kiosia.b2wchallenge.vo.OrderItemVo;
import com.kiosia.b2wchallenge.vo.OrderVo;
import com.kiosia.b2wchallenge.vo.ProductVo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Ignore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class Steps {
  private final String SERVER_URL = "http://localhost:8080";
  private final String PRODUCTS_ENDPOINT = "/product";
  private final String ORDERS_ENDPOINT = "/order";

  private RestTemplate restTemplate;

  public Steps() {
    restTemplate = new RestTemplate();
  }

  private String orderEndpoint() {
    return SERVER_URL + ORDERS_ENDPOINT;
  }

  private String productEndpoint() {
    return SERVER_URL + PRODUCTS_ENDPOINT;
  }

  ResponseEntity<OrderVo> getOrder(Long id) {
    return restTemplate.getForEntity(orderEndpoint() + "/" + id, OrderVo.class);
  }

  ResponseEntity<OrderVo> postOrder(final OrderVo requestBody) {
    return restTemplate.postForEntity(orderEndpoint(), requestBody, OrderVo.class);
  }

  ResponseEntity<ProductVo> getProduct(Long id) {
    return restTemplate.getForEntity(productEndpoint() + "/" + id, ProductVo.class);
  }

  ResponseEntity<ProductVo> postProduct(final ProductVo requestBody) {
    return restTemplate.postForEntity(productEndpoint(), requestBody, ProductVo.class);
  }

  private ResponseEntity<OrderVo> orderResponse;
  private OrderVo orderRequest;
  private ResponseEntity<ProductVo> productResponse;
  private ProductVo productRequest;
  private Long productId;
  private Long orderId;

  // Product steps

  @Given("the user wants to post a new Product")
  public void prepareProduct() {
    this.productRequest = new ProductVo();
  }

  @Given("the request has the field name with value {string}")
  public void the_request_has_the_field_name_with_value(String name) {
    productRequest.setName(name);
  }

  @Given("the request has the field description with value {string}")
  public void the_request_has_the_field_description_with_value(String description) {
    productRequest.setDescription(description);
  }

  @Given("the request has the field current_price with value {double}")
  public void the_request_has_the_field_current_price_with_value(Double currentPrice) {
    productRequest.setCurrentPrice(currentPrice);
  }

  @Given("the request has the field stock with value {int}")
  public void the_request_has_the_field_current_price_with_value(Integer stock) {
    productRequest.setStock(stock);
  }

  @When("the user posts that Product")
  public void the_user_posts_that_product() {
    productResponse = this.postProduct(productRequest);
    productId = productResponse.getBody().getId();
  }

  @Then("the product response should contain status code {int}")
  public void the_product_response_should_contain_status_code(Integer expectedStatusCode) {
    assertThat(productResponse.getStatusCodeValue()).isEqualTo(expectedStatusCode);
  }

  @Then("the response should contain a product id")
  public void the_response_should_contain_a_product_id() {
    assertThat(productId).isNotNull();
  }

  @Then("the response should contain the field name with value {string}")
  public void the_response_should_contain_the_field_name_with_value(String expectedName) {
    assertThat(productResponse.getBody().getName()).isEqualTo(expectedName);
  }

  @Then("the response should contain the field description with value {string}")
  public void the_response_should_contain_the_field_description_with_value(String expectedDescription) {
    assertThat(productResponse.getBody().getDescription()).isEqualTo(expectedDescription);
  }

  @Then("the response should contain the field stock with value {int}")
  public void the_response_should_contain_the_field_stock_with_value(Integer expectedStock) {
    assertThat(productResponse.getBody().getStock()).isEqualTo(expectedStock);
  }

  @Then("the response should contain the field current_price with value {double}")
  public void the_response_should_contain_the_field_current_price_with_value(Double expectedPrice) {
    assertThat(productResponse.getBody().getCurrentPrice()).isEqualTo(expectedPrice);
  }

  @When("the user wants to fetch that Product")
  public void the_user_wants_to_fetch_that_Product() {
    productResponse = this.getProduct(this.productId);
  }

  // Order steps

  @Given("^the user wants to post an Order$")
  public void prepareOrder() {
    this.orderRequest = new OrderVo();
  }

  @Given("the request has the field date with value {string}")
  public void the_request_has_the_field_date_with_value(String date) {
    orderRequest.setDate(date);
  }

  @Given("the request has the field customer with value {string}")
  public void the_request_has_the_field_customer_with_value(String customerName) {
    orderRequest.setCustomer(customerName);
  }

  @Given("the request has the field shipping with value {double}")
  public void the_request_has_the_field_shipping_with_value(Double shipping) {
    orderRequest.setShipping(shipping);
  }

  @Given("the request has the inserted item on the item list with quantity {int}")
  public void the_request_has_inserted_item_on_list(Integer quantity) {
    List<OrderItemVo> items = new ArrayList<>();
    OrderItemVo orderItemVo = OrderItemVo.newBuilder()
        .withQuantity(quantity)
        .withProduct(productId)
        .build();

    items.add(orderItemVo);
    orderRequest.setItems(items);
  }

  @When("the user posts that Order")
  public void the_user_posts_that_Order() {
    orderResponse = this.postOrder(orderRequest);
    orderId = orderResponse.getBody().getId();
  }

  @Then("the response should contain an Order id")
  public void the_response_should_contain_an_order_id() {
    assertThat(orderId).isNotNull();
  }

  @Then("the order response should contain status code {int}")
  public void the_order_response_should_contain_status_code(Integer expectedStatusCode) {
    assertThat(orderResponse.getStatusCodeValue()).isEqualTo(expectedStatusCode);
  }

  @When("the user wants to fetch that Order")
  public void the_user_wants_to_fetch_that_Order() {
    orderResponse = this.getOrder(this.orderId);
  }

  @Then("the response should contain the field date with value {string}")
  public void the_response_should_contain_the_field_date_with_value(String expectedDate) throws ParseException {
    assertThat(orderResponse.getBody().getDate()).isEqualTo(expectedDate);
  }

  @Then("the response should contain the field customer with value {string}")
  public void the_response_should_contain_the_field_customer_with_value(String expectedCustomer) {
    assertThat(orderResponse.getBody().getCustomer()).isEqualTo(expectedCustomer);
  }

  @Then("the response should contain the field shipping with value {double}")
  public void the_response_should_contain_the_field_shipping_with_value(Double expectedShipping) {
    assertThat(orderResponse.getBody().getShipping()).isEqualTo(expectedShipping);
  }

  @Then("the response should contain the item with id {long} at the index {int}")
  public void the_response_should_contain_the_items_with_id(Long productId, Integer index) {
    assertThat(orderResponse.getBody().getItems().get(index).getProduct()).isEqualTo(productId);
  }

  @Then("the response should contain the item with quantity {int} at the index {int}")
  public void the_response_should_contain_the_items_with_qtt(Integer quantity, Integer index) {
    assertThat(orderResponse.getBody().getItems().get(index).getQuantity()).isEqualTo(quantity);
  }

  @Then("the response should contain the item with price {double} at the index {int}")
  public void the_response_should_contain_the_items_with_price(Double price, Integer index) {
    assertThat(orderResponse.getBody().getItems().get(index).getPrice()).isEqualTo(price);
  }
}
