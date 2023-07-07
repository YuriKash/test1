Feature: Search an employee

  Background:

    When user enters valid admin username and password
    And user click on login button
    Then user is successfully logged in the application
    When  user clicks on PIM option and Employee list option

  @regression @smoke @sprint3 @background
  Scenario: Search employee by id
    And user enters valid employee id
    And user clicks on search button
    Then user is able to see employee information

    @regression @smoke @sprint20 @background
  Scenario: Search employee by name
    And user enters valid employee name in name text box
    And user clicks on search button
    Then user is able to see employee information