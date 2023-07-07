Feature: Adding employee in HRMS application

  Background:
    * user enters valid admin username and password
    * user click on login button
    * user is successfully logged in the application
    * user clicks on PIM option
    * user clicks on add employee button

  @test
  Scenario: adding one employee

    * user enters firstname and lastname
    * user clicks on save button
    * employee added successfully

  @sample
  Scenario: Adding one employee from feature file
    When user enters "adam" and "lovely" and "farwa"
    And user clicks on save button
    Then employee added successfully

  @outline
  Scenario Outline: adding multiple employees using scenario outline
    When user enters "<firstName>" and "<middleName>" and "<lastName>" in data driven format
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | middleName | lastName |
      | leny      | darzi      | fraud    |
      | paster    | surma      | shalli   |
      | sana      | caty       | aim      |

  @datatable
  Scenario: adding multiple employees using data table

    When user enters firstname and middlename and lastname and verify employee has added
      | firstName | middleName | lastName |
      | zara      | ms         | zaca     |
      | mary      | ms         | django   |
      | sada      | ms         | adam     |


    @excel
  Scenario: adding multiple employees usimng excel file
    When user adds multiple employees using excel from "EmployeeDataBatch16" and verify it
