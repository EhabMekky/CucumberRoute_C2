Feature: Shopping Automation
  Scenario: Testing the authentication
    Given I go to the website
    When I click on the Sign in button
    And I enter my login credentials
    Then I should be logged in successfully