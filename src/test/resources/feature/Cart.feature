Feature: Access the Cart Page
  Scenario: Navigate to the cart page
    Given I am logged in
    When I click on the Cart button
    Then I should be on the Cart page