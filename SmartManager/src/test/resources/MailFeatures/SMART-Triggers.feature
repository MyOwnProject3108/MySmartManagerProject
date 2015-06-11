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
    Then I Should See ESP Connection "<Verification>" on Mail Triggers Page

    Examples: Connections
      | Name      | ESP       | Verification |
      | Silverpop | Silverpop | Silverpop    |
      | ECircle   | ECircle   | ECircle      |
      | SmartCast | SmartCast | SmartCast    |

  @TestOnlyESPConnection
  Scenario: Create Test only ESP Connection
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I Create Test Connection "ECircleTest" for ESP "ECircle"
    Then I Should not See ESP Connection "ECircleTest" on Mail Triggers Page

  @EditESPConnection
  Scenario: Edit ESP Connection
    Given I Edit ESP Connection "ECircle"
    Then I Should See ESP Connection "AutoECircle" on Mail Triggers Page

  @DeleteESPConnection
  Scenario: Delete ESP Connection
    Given I Delete ESP Connection "ECircle"
    Then I Should not See ESP Connection "ECircle" on Mail Triggers Page

  @NoDeleteESPConnection
  Scenario: Click No to Delete ESP Connection
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Connections"
    And I click No for Delete ESP Connection "ECircle"
    Then I Should See ESP Connection "ECircle" on Mail Triggers Page

  @DeactiveESPConnection
  Scenario: Deactive ESP Connection
    Given I click on "Triggers" option in "Mail"
    When click on button "ESP Connections"
    And I deactivate "ECircle" ESP Connection
    Then I should see Message "Switched off"

  @ActivatedeactivatedESPConnection
  Scenario: Activate a deactivated ESP Connection
    Given I click on "Triggers" option in "Mail"
    When click on button "ESP Connections"
    And I activate "ECircle" ESP Connection
    Then I should see Message "Switched on"

  @EditDeleteButtonConnection
  Scenario: Verify delete button is disabled on Edit
    Given I click on "Triggers" option in "Mail"
    When click on button "ESP Connections"
    And I click Edit button for "ECircle" ESP Connection
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @NewDeleteButtonConnection
  Scenario: Verify delete button is disabled for new Connection
    Given I click on "Triggers" option in "Mail"
    When click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I Create New Connection "ECircle" for ESP "ECircle"
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @CreateESPAction
  Scenario Outline: Create an ESP Action
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Actions"
    And click on button "Add an ESP Action"
    And I Create Action "<Name>" for ESP "<Connection>"

    Examples: Actions
      | Name      | Connection |
      | ECircle   | ECircle    |
      | SmartCast | SmartCast  |
      | Silverpop | Silverpop  |

  @TestOnlyESPAction
  Scenario: Create Test only ESP Connection
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Actions"
    And click on button "Add an ESP Action"
    And I Create Test Action "ECircleTest" for ESP "ECircle"
    Then I Should not See ESP Action "ECircleTest" on Mail Triggers Page

  @EditESPAction
  Scenario: Edit ESP Action
    Given I Edit ESP Action "ECircle"
    Then I Should See ESP Action "AutoECircle" on Mail Triggers Page
