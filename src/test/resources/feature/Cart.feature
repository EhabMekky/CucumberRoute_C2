Feature: Access the Cart Page

  Background: User must be logged in
    Given I navigate to the website
    When I open the login form
    And I specify my credentials
    Then I should see the welcome message

  Scenario: Navigate to the cart page
    When I click on the Cart button
    Then I should be on the Cart page