Feature: Order endpoints
    Scenario: Post Order
        Given the user wants to post a new Product
        And the request has the field name with value "Product 1"
        And the request has the field description with value "Product 1 is the best product of its kind!"
        And the request has the field stock with value 20
        And the request has the field current_price with value 19.99
        When the user posts that Product
        Then the product response should contain status code 201
        Then the user wants to post an Order
        And the request has the field date with value "2019-04-05"
        And the request has the field customer with value "CustomerName"
        And the request has the field shipping with value 12.12
        And the request has the inserted item on the item list with quantity 1
        When the user posts that Order
        Then the order response should contain status code 201
        And the response should contain an Order id
        And the response should contain the field date with value "2019-04-05"
        And the response should contain the field customer with value "CustomerName"
        And the response should contain the field shipping with value 12.12
        And the response should contain the item with id 1 at the index 0
        And the response should contain the item with quantity 1 at the index 0
        And the response should contain the item with price 19.99 at the index 0
        When the user wants to fetch that Order
        Then the order response should contain status code 200
        And the response should contain an Order id
        And the response should contain the field date with value "2019-04-05"
        And the response should contain the field customer with value "CustomerName"
        And the response should contain the field shipping with value 12.12
        And the response should contain the item with id 1 at the index 0
        And the response should contain the item with quantity 1 at the index 0
        And the response should contain the item with price 19.99 at the index 0