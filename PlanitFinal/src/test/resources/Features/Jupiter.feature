Feature: Testing Jupiter Toys website

  Background: User is in the Jupiter Toys website
    Given Browser is open
    When User enters the Jupiter Toys website link.
    Then User land on Jupiter Toys website home page

  Scenario Outline: Testing the mandatory fields errors in the contact page.
    When User clicks on contact
    And User click on submit button
    Then Error message is shown for mandatory fields
    When User enter <forename> and <email> and <message> in the mandatory fields
    Then Error messages are disappeard

    Examples: 
      | forename | email           | message      |
      | Alan     | alan@planit.com | Hi I am Alan |

  Scenario Outline: Verify user is able to successfully submit feedbacks.
    When User clicks on contact
    When User enter <forename> and <email> and <message> in the mandatory fields
    And User click on submit button
    Then User receive successful submission message

    Examples: 
      | forename | email              | message         |
      | Alan     | alan@planit.com    | Hi I am Alan    |
      | Oliver   | Oliver@planit.com  | Hi I am Oliver  |
      | William  | William@planit.com | Hi I am William |
      | Amelia   | Amelia@planit.com  | Hi I am Amelia  |
      | Mia      | Mia@planit.com     | Hi I am Mia     |

  Scenario: Verify that the user can buy multiple products and in the cart, individual price, subtotal price, and total prices are correct.
    When User clicks on Start Shopping
    And User buy product 'Stuffed Frog' with quanity '2'
    And User buy product 'Fluffy Bunny' with quanity '5'
    And User buy product 'Valentine Bear' with quanity '3'
    Then User clicks on Cart
    And verify the price for each item
    And verify the subtotal for each product
    And verify the total price
