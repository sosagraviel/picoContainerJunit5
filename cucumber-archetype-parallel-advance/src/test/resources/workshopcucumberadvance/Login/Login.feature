@Regression @Login
Feature: Login

  @Logins
  Scenario: Login successful with correct credentials
    Given An "normalAccount" is logged in the system
    When Login page is displayed
    And User completes Email field with "normalAccount" credentials
    And User completes Password field with "normalAccount" credentials
    And User clicks on Login button
    Then the Home page is displayed

  @Login
  Scenario Outline: Login failed with empty credentials
    When Login page is displayed
    And User completes Email field with any credentials <user>
    And User completes Password field with any credentials <password>
    And the Login button is "Disabled"

    Examples:
      | user           | password   |
      | ""             | ""         |
      | ""             | "12345678" |
      | "graviel.sosa" | ""         |

  @Login
  Scenario Outline: Login failed with wrong credentials
    When Login page is displayed
    And User completes Email field with any credentials <user>
    And User completes Password field with any credentials <password>
    And User clicks on Login button
    Then the Toast message is Displayed

    @RunThisTag
    Examples:
      | user           | password         |
      | "wrong.user"   | "12345678"       |
      | "graviel.sosa" | "wrong.password" |
      | "wrong.user"   | "wrong.password" |

    @SkipThisTag
    Examples:
      | user               | password         |
      | "wrong.user.wrong" | "87654321"       |
      | "graviel.wrong"    | "password.wrong" |
      | "wrong.user.wrong" | "password.wrong" |


  @Login
  Scenario: Login failed with wrong credentials data-driven
    When Login page is displayed
    And Login failed with wrong credentials
      | user         | password       |
      | wrong.user   | 12345678       |
      | graviel.sosa | wrong.password |
      | wrong.user   | wrong.password |