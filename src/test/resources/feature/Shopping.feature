Feature: Shopping Flow

  Scenario: Complete shopping flow from login to cart
    Given I navigate to the website
    When I open the login form
    And I specify my credentials
    Then I should see the welcome message
    When I click on the Cart button
    Then I should be on the Cart page