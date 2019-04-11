Feature: Product endpoints
    Scenario: Post and Get Product
        Given the user wants to post a new Product
        And the request has the field name with value "Product 1"
        And the request has the field description with value "Product 1 is the best product of its kind!"
        And the request has the field stock with value 20
        And the request has the field current_price with value 19.99
        When the user posts that Product
        Then the product response should contain status code 201
        And the response should contain a product id
        And the response should contain the field name with value "Product 1"
        And the response should contain the field description with value "Product 1 is the best product of its kind!"
        And the response should contain the field stock with value 20
        And the response should contain the field current_price with value 19.99
        When the user wants to fetch that Product
        Then the product response should contain status code 200
        And the response should contain a product id
        And the response should contain the field name with value "Product 1"
        And the response should contain the field description with value "Product 1 is the best product of its kind!"
        And the response should contain the field stock with value 20
        And the response should contain the field current_price with value 19.99