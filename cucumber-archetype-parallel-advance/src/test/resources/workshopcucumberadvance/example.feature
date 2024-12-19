@@Regression
Feature: An example of picocontainer

  @ExampleLogin
  Scenario: An example of picocontainer to login
    Given Login page is displayed
    When Lets send only "graviel.sosa" user
    And Lets send only the "12345678" password
    And User brings the whole needed data to login
    And User clicks on Login button
    Then the Home page is displayed
