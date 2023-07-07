Feature: Login related scenarios

  Background:


  @sprint1 @regression @smoke @login
  Scenario: Valid admin login
    When user enters valid admin username and password
    And user click on login button
    Then user is successfully logged in the application

  @employee @sprint1
  Scenario: valid ess login
    When user enters ess username and password
    And user click on login button
    Then user is successfully logged in the application

  @invalid @sprint1
  Scenario: invalid admin login
    When user enters invalid admin username and password
    And user click on login button
    Then error message is displayed
@negative
  Scenario Outline: negative login test

    When user enters "<username>" and "<password>" and verifying the "<error>" for the combination
    Examples:
      | username | password    | error                    |
      | admin    | hgghg       | Invalid credentials      |
      | admin1   | Hum@nhrm123 | Invalid credentials      |
      |          | Hum@nhrm123 | Username cannot be empty |
      | admin    |             | Password cannot be empty |