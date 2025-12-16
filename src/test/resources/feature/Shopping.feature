Feature: Shopping Automation
  Scenario: Testing the authentication
    Given I go to the website
    When I click on the Sign in button
    And I specify my credentials
    Then I should be logged in successfully