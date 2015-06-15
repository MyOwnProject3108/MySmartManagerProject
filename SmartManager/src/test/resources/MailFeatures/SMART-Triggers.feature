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
    Given I goto ESP Connections on Mail Triggers Page
    And click on button "Add an ESP connection"
    And I Create Test Connection "ECircleTest" for ESP "ECircle"
    Then I Should not See ESP Connection "ECircleTest" on Mail Triggers Page

  @EditESPConnection
  Scenario: Edit ESP Connection
    Given I Edit ESP Connection "ECircle"
    Then I Should See ESP Connection "AutoECircleConnection" on Mail Triggers Page

  @DeleteESPConnection
  Scenario: Delete ESP Connection
    Given I Delete ESP Connection "ECircle"
    Then I Should not See ESP Connection "ECircle" on Mail Triggers Page

  @NoDeleteESPConnection
  Scenario: Click No to Delete ESP Connection
	Given I goto ESP Connections on Mail Triggers Page
    And I click No for Delete ESP Connection "ECircle"
    Then I Should See ESP Connection "ECircle" on Mail Triggers Page

  @DeactiveESPConnection
  Scenario: Deactive ESP Connection
	Given I goto ESP Connections on Mail Triggers Page
    And I deactivate "ECircle" ESP Connection
    Then I should see Message "Switched off"

  @ActivatedeactivatedESPConnection
  Scenario: Activate a deactivated ESP Connection
    Given I goto ESP Connections on Mail Triggers Page
    And I activate "ECircle" ESP Connection
    Then I should see Message "Switched on"

  @EditDeleteButtonConnection
  Scenario: Verify delete button is disabled for Edit ESP Connection
	Given I goto ESP Connections on Mail Triggers Page
    And I click Edit button for "ECircle" ESP Connection
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @NewDeleteButtonConnection
  Scenario: Verify delete button is disabled for new ESP Connection
    Given I goto ESP Connections on Mail Triggers Page
    And click on button "Add an ESP connection"
    And I Create Connection "ECircle" for ESP "ECircle"
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @CreateESPAction
  Scenario Outline: Create an ESP Action
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Actions"
    And click on button "Add an ESP Action"
    And I Create Action "<Name>" for ESP "<Connection>"

    Examples: Actions
      | Name      | Connection | Verification|
      | ECircle   | ECircle    | ECircle     |
      | SmartCast | SmartCast  | SmartCast   |
      | Silverpop | Silverpop  | Silverpop   |

  @TestOnlyESPAction
  Scenario: Create Test only ESP Connection
    Given I goto ESP Actions on Mail Triggers Page
    And click on button "Add an ESP Action"
    And I Create Test Action "ECircleTest" for ESP "ECircle"
    Then I Should not See ESP Action "ECircleTest" on Mail Triggers Page

  @EditESPAction
  Scenario: Edit ESP Action
    Given I Edit ESP Action "ECircle"
    Then I Should See ESP Action "AutoECircleAction" on Mail Triggers Page
    
   @DeleteESPAction
   Scenario: Delete ESP Action
    Given I Delete ESP Action "ECircle"
    Then I Should not See ESP Action "ECircle" on Mail Triggers Page
    
  @NoDeleteESPAction
  Scenario: Click No to Delete ESP Action
	Given I goto ESP Actions on Mail Triggers Page
    And I click No for Delete ESP Action "ECircle"
    Then I Should See ESP Action "ECircle" on Mail Triggers Page
    
  @EditDeleteButtonAction
  Scenario: Verify delete button is disabled for Edit ESP Action
	Given I goto ESP Actions on Mail Triggers Page
    And I click Edit button for "ECircle" ESP Action
    Then I Should See disabled delete button for "ECircle" ESP Action
    
  @NewDeleteButtonAction
  Scenario: Verify delete button is disabled for new ESP Action
    Given I goto ESP Actions on Mail Triggers Page
    And click on button "Add an ESP Action"
	And I Create Action "ECircle" for ESP "ECircle"
    Then I Should See disabled delete button for "ECircle" ESP Action
    
  @CreateESPTrigger
  Scenario Outline: Create an ESP Trigger
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button " Add a Trigger"
    And I Create Trigger with name "<TriggerName>" for "<ESPAction>" from "<Position>"

    Examples: Triggers
      | TriggerName      | ESPAction | Position|
      | Abandoned Browse | ECircle   | 2       |
      | Abandoned Basket | SmartCast | 1       |
            
   @EditESPTrigger
   Scenario:  Edit ESP Trigger
   Given I Edit ESP Trigger "Abandoned Browse"  
   Then I Should See ESP Trigger "Edit Abandoned Browse" on Mail Triggers Page
   
   @DeleteESPTrigger
   Scenario: Delete ESP Trigger
   Given I Delete ESP Trigger "Abandoned Browse"
   Then I Should not See ESP Trigger "Abandoned Browse" on Mail Triggers Page
   
  @NoDeleteESPTrigger
  Scenario: Click No to Delete ESP Trigger
	Given I goto ESP Triggers on Mail Triggers Page
    And I click No for Delete ESP Trigger "Abandoned Browse"
    Then I Should See ESP Trigger "Abandoned Browse" on Mail Triggers Page
    
