Feature: Register new user on Automation Exercise site

  @test
  Scenario: Successful user registration
    Given I launch the Automation Exercise website
    When I navigate to Signup page
    And I enter name "anshika2" and email "anshika2@test.com"
    And I fill the registration form with valid details
    And I submit the registration form
    Then I should see "ACCOUNT CREATED!" message
    And I should be logged in as "anshika2"
  
  @test  
  Scenario Outline: Login with different credentials
    Given I launch the Automation Exercise website
    When I navigate to Signup page
    And I enter login email "<email>" and password "<password>"
    And I click login button
    Then I should see login result "<result>" as "<username>"

    Examples:
      | email                     | password      | result    | username  |
      | anshika2@test.com         | Password123   | success   | anshika2  |
      | invaliduser@example.com   | wrongPass     | failure   |           |  
