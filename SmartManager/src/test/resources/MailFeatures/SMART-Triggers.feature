Feature: SMART-Triggers Test

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @CreateESPConnection
  Scenario Outline: Create an ESP Connection
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I Create Connection "<Name>" for ESP "<ESP>"

    Examples: Connections
      | Name                | ESP       |
      | SilverPop           | Silverpop |
      | ECircle             | ECircle   |
      | SmartCastConnection | SmartCast |
