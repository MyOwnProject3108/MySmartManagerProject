Feature: S-Merchandising

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page

  Scenario: Create Simple Merchandising Campaign
    And I search for site "demostoredev"
    When I click on "Create a New Campaign" option in "Merchandising"
    Then I should be on Merchandising "Create a New Campaign" page
    And I Create Simple Campaign with name "AutoCreate"
    Then I Should See Campaign "AutoCreate" on Overview Page
