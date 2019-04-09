Feature: Order endpoints
    Scenario: Post Order
        Given the user wants to post an Order
        And the request has the field date with value "2019-04-05"
        And the request has the field customer with value "CustomerName"
        And the request has the field shipping with value 12.30
        And the request has items with values:
        | 1 | 1 | 44.50 |
        | 2 | 1 | 31.99 |
        When the user posts that Order
        Then the response should contain status code 200
        And the response should contain an id
        And the response should contain the field date with value "2019-04-05"
        And the response should contain the field customer with value "CustomerName"
        And the response should contain the field shipping with value 12.30
        And the response should contain items with values:
        | 1 | 1 | 44.50 |
        | 2 | 1 | 31.99 |