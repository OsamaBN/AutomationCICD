
@tag
Feature:Purchase the order from Ecommerce Website

Background: 
Given I landed on Ecommerce Page

  @tag1
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username                   | password               | productName |
      | osama.nadeem@gmail.com     | Ilikeautomation@#07    | ZARA COAT 3 |
     
