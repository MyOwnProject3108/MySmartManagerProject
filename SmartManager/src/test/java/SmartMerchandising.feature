Feature: S-Merchandising

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  Scenario: Create Simple Merchandising Campaign
    When I click on "Create a New Campaign" option in "Merchandising"
    Then I should be on Merchandising "Create a New Campaign" page
    And I Create Simple Campaign with name "AutoCreate"
    Then I Should See Campaign "AutoCreate" on Overview Page

  Scenario: Edit Campaign
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    Then I should be on "edit" Page

  Scenario: Activate Campaign
    Given I goto Campaign "AutoCreate"
    And I activate Campaign "AutoCreate"
    Then Campaign should be Activated

  Scenario: Pause Campaign
    Given I Pause Campaign "AutoCreate"
    Then Campaign Should be Paused

  Scenario: Duplicate Campaign
    Given I goto Campaign "AutoCreate"
    And I Duplicate Campaign "AutoCreate"
    Then I Should See Campaign "AutoCreate copy" on Overview Page

  Scenario: Delete Campaign
    Given I Delete Campaign "AutoCreate copy"
    Given I Delete Campaign "AutoCreate"
    Then I should not see "AutoCreate copy"
    Then I should not see "AutoCreate"
  Scenario Outline: Message Validation
    Given I Create Campaign with <Name>
    Then I should see Message <Message>

    Examples: Validation Messages
      | Name   | Message | Field |
      | Auto_1 | Error   |       |
      | Auto3  | Error   |       |
      | 1234   | Error   |       |
    