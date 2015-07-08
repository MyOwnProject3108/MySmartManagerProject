Feature: SMART-Triggers Test

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page

  @CreateESPConnection
  Scenario Outline: Create an ESP Connection
    When I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I Create Connection "<Name>" for ESP "<ESP>"
    Then I Should See ESP Connection "<Verification>" on Mail Triggers Page

    Examples: Connections
      | Name      | ESP       | Verification |
      | Silverpop | Silverpop | Silverpop    |
      | ECircle   | ECircle   | ECircle      |
      | SmartCast | SmartCast | SmartCast    |

  @TestOnlyESPConnection
  Scenario: Create Test only ESP Connection
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I Create Test Connection "ECircleTest" for ESP "Teradata (ECircle)"
    Then I Should not See ESP Connection "ECircleTest" on Mail Triggers Page

  @NoDeleteESPConnection
  Scenario: Click No to Delete ESP Connection
    Given I click on button "ESP Connections"
    And I click No to Delete ESP Connection "ECircle"
    Then I Should See ESP Connection "ECircle" on Mail Triggers Page

  @DeactiveESPConnection
  Scenario: Deactive ESP Connection
    Given I click on button "ESP Connections"
    And I deactivate "ECircle" ESP Connection
    Then I should see Message "Switched off"

  @ActivatedeactivatedESPConnection
  Scenario: Activate a deactivated ESP Connection
    Given I activate "ECircle" ESP Connection
    Then I should see Message "Switched on"

  @EditDeleteButtonConnection
  Scenario: Verify delete button is disabled for Edit ESP Connection
    Given I click on button "ESP Connections"
    And I click Edit button for "ECircle" ESP Connection
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @EditESPConnection
  Scenario: Edit ESP Connection
    Given I click on button "ESP Connections"
    And I click Edit button for "ECircle" ESP Connection
    And I set "name" field as "AutoECircleConnection"
    When I click on button "Test ESP Connection"
    And I Wait for Element "notification"
    Then I should see Message "Test passed successfully"
    And I Wait for Element "btn btn-primary btn-success"
    When I click on button "Save ESP Connection"
    Then I should see Message "Successfully saved"
    When I click on button "ESP Connections"
    Then I Should See ESP Connection "AutoECircleConnection" on Mail Triggers Page

  @DeleteESPConnection
  Scenario: Delete ESP Connection
    Given I click on button "ESP Connections"
    Given I Delete ESP Connection "AutoECircleConnection"
    Then I Should not See ESP Connection "AutoECircleConnection" on Mail Triggers Page

  @NewDeleteButtonConnection
  Scenario: Verify delete button is disabled for new ESP Connection
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I Create Connection "ECircle" for ESP "ECircle"
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @CreateESPAction
  Scenario Outline: Create an ESP Action
    When I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I Create Action "<Name>" for ESP "<Connection>"
    Then I Should See ESP Action "<Verification>" on Mail Triggers Page

    Examples: Actions
      | Name      | Connection | Verification |
      | ECircle   | ECircle    | ECircle      |
      | Silverpop | Silverpop  | Silverpop    |

  #| SmartCast     | SmartCast  | SmartCast    |
  @TestOnlyESPAction
  Scenario: Create Test only ESP Connection
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I Create Test Action "ECircleTest" for ESP "ECircle"
    Then I Should not See ESP Action "ECircleTest" on Mail Triggers Page

  @NoDeleteESPAction
  Scenario: Click No to Delete ESP Action
    Given I click on button "ESP Actions"
    And I click No to Delete ESP Action "ECircle"
    Then I Should See ESP Action "ECircle" on Mail Triggers Page

  @EditDeleteButtonAction
  Scenario: Verify delete button is disabled for Edit ESP Action
    Given I click on button "ESP Actions"
    And I click Edit button for "ECircle" ESP Action
    Then I Should See disabled delete button for "ECircle" ESP Action

  @EditESPAction
  Scenario: Edit ESP Action
    Given I click on button "ESP Actions"
    When I click on link "ECircle"
    And I set "action-name" field as "AutoECircleAction"
    And I enter "test@peerius.com" in field "email_address"
    When I click on button "Test ESP Action"
    And I Wait for Element "notification success"
    Then I should see Message "Test passed successfully"
    And I Wait for Element "btn-success"
    When I click on button "Save ESP Action"
    Then I should see Message "Successfully saved"
    And I Should See ESP Action "AutoECircleAction" on Mail Triggers Page

  @DeleteESPAction
  Scenario: Delete ESP Action
    Given I click on button "ESP Actions"
    Given I Delete ESP Action "AutoECircleAction"
    Then I Should not See ESP Action "AutoECircleAction" on Mail Triggers Page

  @NewDeleteButtonAction
  Scenario: Verify delete button is disabled for new ESP Action
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I Create Action "ECircle" for ESP "ECircle"
    Then I Should See disabled delete button for "ECircle" ESP Action

  @CreateESPTrigger
  Scenario Outline: Create an ESP Trigger
    When I click on button " Add a Trigger"
    And I Create Trigger with name "<TriggerName>" for "<ESPAction>" from "<Position>"

    Examples: Triggers
      | TriggerName      | ESPAction | Position |
      | Abandoned Browse | ECircle   | 2        |
      | Abandoned Basket | Silverpop | 1        |

  @NoDeleteESPTrigger
  Scenario: Click No to Delete ESP Trigger
    Given I click No to Delete ESP Trigger "Abandoned Browse"
    Then I Should See ESP Trigger "Abandoned Browse" on Mail Triggers Page

  @ActivateESPTrigger
  Scenario: Activate a deactivated ESP Trigger
    Given I activate "Abandoned Basket" ESP Trigger
    Then I should see Message "Switched on"

  @DeActivateESPTrigger
  Scenario: Deactivate ESP Trigger
    Given I deactivate "Abandoned Basket" ESP Trigger
    Then I should see Message "Switched off"

  @EditDeleteButtonTrigger
  Scenario: Verify delete button is disabled for Edit ESP Trigger
    Given I click Edit button for "Abandoned Browse" ESP Trigger
    Then I Should See disabled delete button for "Abandoned Browse" ESP Trigger

  @EditESPTrigger
  Scenario: Edit ESP Trigger
    Given I click on link "Abandoned Browse"
    When I set "triggers_name" field as "Edit Abandoned Browse"
    And I click on button "Save Trigger"
    And I Wait for Element "notification success"
    Then I Should See ESP Trigger "Edit Abandoned Browse" on Mail Triggers Page

  @DeleteESPTrigger
  Scenario: Delete ESP Trigger
    Given I Delete ESP Trigger "Edit Abandoned Browse"
    Then I Should not See ESP Trigger "Edit Abandoned Browse" on Mail Triggers Page

  @NewDeleteButtonTrigger
  Scenario: Verify delete button is disabled for new ESP Trigger
    Given I click on button "Add a Trigger"
    And I Create Trigger with name "Abandoned Browse" for "ECircle" from "2"
    Then I Should See disabled delete button for "Abandoned Browse" ESP Trigger

  @DeleteTestTriggers
  Scenario Outline: Delete AbandonedBasket Trigger After Tests
    Given I Delete ESP Trigger "<Trigger>"
    Then I Should not See ESP Trigger "<Trigger>" on Mail Triggers Page

    Examples: Test Triggers
      | Trigger          |
      | Abandoned Browse |
      | Abandoned Basket |

  @DeleteEcircleAction
  Scenario Outline: Delete ESP Action After Test
    Given I click on button "ESP Actions"
    And I Delete ESP Action "<Action>"
    Then I Should not See ESP Action "<Action>" on Mail Triggers Page

    Examples: Delete Data
      | Action    |
      | Silverpop |
      | ECircle   |
      | SmartCast |

  @DeleteConnectionAfterTest
  Scenario Outline: Delete ESP Connections After Test
    Given I click on button "ESP Connections"
    And I Delete ESP Connection "<Connection>"
    Then I Should not See ESP Connection "<Connection>" on Mail Triggers Page

    Examples: Test Connections
      | Connection |
      | ECircle    |
      | SmartCast  |
      | Silverpop  |

  