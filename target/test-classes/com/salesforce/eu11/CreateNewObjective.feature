@Test001
Feature: As a user of Salesforce system I would like to create a new objective for my appraisal

  Background:
    Given I am on Salesforce login page
    When I enter valid "trial7382@trial.fairsail.com" and password "fairsail01"
    Then I see Salesforce homepage

  Scenario Outline: Adding a booking with valid details
    When I navigate to Workforce eXperience tab
    And I create new personal objective
    Then I see New Objective view
    When I enter the Description details: Objective Name "<Objective Name>" and Description "<Description>" and  Measure "<Measure>"
    And I select Contribution to business details: Strategic Goal "<Goal>" and Contributes To "<Contributes To>"
    And I select Key dates details: Start Date "<Start Date>" End Date "<End Date>" Next Review Date "<New Review Date>"
    And I enter Attributes: Weight "<Weight>" and Priority "<Priority>" and select Required For Bonus "<Required For Bonus>"
    And I select Visibility: Is Private "<Is Private>"
    And I click the Save button
    Then Check record created for valid details "<Objective Name>"
    Examples:
      | Objective Name | Description | Measure      | Goal      | Contributes To | Start Date | End Date   | New Review Date | Weight | Priority | Required For Bonus | Is Private |
      | test obj 02    | test desc   | test measure | FINANCIAL |                | 20/02/2016 | 19/02/2017 | 10/02/2018      | 10     | HIGH     | YES                | YES        |

  Scenario Outline: Booking with invalid details should not be added
    When I navigate to Workforce eXperience tab
    And I create new personal objective
    Then I see New Objective view
    When I enter the Description details: Objective Name "<Objective Name>" and Description "<Description>" and  Measure "<Measure>"
    And I select Contribution to business details: Strategic Goal "<Goal>" and Contributes To "<Contributes To>"
    And I select Key dates details: Start Date "<Start Date>" End Date "<End Date>" Next Review Date "<New Review Date>"
    And I enter Attributes: Weight "<Weight>" and Priority "<Priority>" and select Required For Bonus "<Required For Bonus>"
    And I select Visibility: Is Private "<Is Private>"
    Then Save button is not enabled
    Examples:
      | Objective Name          | Description      | Measure      | Goal      | Contributes To | Start Date | End Date   | New Review Date | Weight | Priority | Required For Bonus | Is Private |
      |                         | obj name missing | test measure | FINANCIAL |                | 20/02/2016 | 19/02/2017 | 10/02/2018      | 10     | HIGH     | YES                | YES        |
      | Obj description missing |                  | test measure | FINANCIAL |                | 20/02/2016 | 19/02/2017 | 10/02/2018      | 10     | HIGH     | YES                | YES        |
      | measure missing         | obj name missing |              | FINANCIAL |                | 20/02/2016 | 19/02/2017 | 10/02/2018      | 10     | HIGH     | YES                | YES        |
      