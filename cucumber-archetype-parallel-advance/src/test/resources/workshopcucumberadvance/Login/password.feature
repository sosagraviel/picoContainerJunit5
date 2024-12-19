@@Regression
Feature: An example

  @PasswordExample
  Scenario: The password example
    Given Login page is displayed
    When Lets send only the "password"
    Then User clicks on Login button to bring user and password
    Then the Home page is displayed
