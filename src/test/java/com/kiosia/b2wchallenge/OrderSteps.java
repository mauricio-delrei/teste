package com.kiosia.b2wchallenge;

import com.kiosia.b2wchallenge.vo.OrderItemVo;
import com.kiosia.b2wchallenge.vo.OrderVo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Ignore;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class OrderSteps {
  private OrderVo request, response;

  @Given("^the user wants to post an Order$")
  public void prepareOrder() {
    this.request = new OrderVo();
  }

  @Given("the request has the field date with value {string}")
  public void the_request_has_the_field_date_with_value(String date) throws ParseException {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    request.setDate(new Date(fmt.parse(date).getTime()));
  }

  @Given("the request has the field customer with value {string}")
  public void the_request_has_the_field_customer_with_value(String customerName) {
    request.setCustomer(customerName);
  }

  @Given("the request has the field shipping with value {double}")
  public void the_request_has_the_field_shipping_with_value(Double shipping) {
    request.setShipping(shipping);
  }

  @Given("the request has items with values:")
  public void the_request_has_items_with_values(DataTable dataTable) {
    final List<List<String>> data = dataTable.asLists();
    request.setItems(new ArrayList<>());
    data.forEach(item -> {
      Long itemId = Long.getLong(item.get(0));
      Integer itemQtt = Integer.getInteger(item.get(1));
      Double itemPrice = Double.parseDouble(item.get(2));

      OrderItemVo orderItemVo = new OrderItemVo(itemId, itemQtt, itemPrice);

      request.addItem(orderItemVo);
    });
  }

  @When("the user posts that Order")
  public void the_user_posts_that_Order() {
    System.out.println(request);
  }

  @Then("the response should contain status code {int}")
  public void the_response_should_contain_status_code(Integer expectedStatusCode) {
    assertThat(response).isEqualTo(expectedStatusCode);
  }

  @Then("the response should contain an id")
  public void the_response_should_contain_an_id() {
    assertThat(response.getId()).isNotNull();

  }

}
