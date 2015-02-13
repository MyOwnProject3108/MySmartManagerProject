Feature: S-Mail

  Scenario: Create S-Mail campaign
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"
    When I click on "Create a New Campaign" option in "Mail"
    Then I should be on "Create a New Campaign" page
