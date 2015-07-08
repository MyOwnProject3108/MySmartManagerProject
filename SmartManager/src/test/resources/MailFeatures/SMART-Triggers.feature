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

  @Connection_nameMandatory
  Scenario: To verify that name is mandatory for Connections
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "name" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Name is required."

  @Connection_noUserName
  Scenario: To verify that UserName is mandatory for Connections
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "username" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"

  @Connection_invalidUserName
  Scenario: To verify that incorrect UserName is not accepted for Connections
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "username" field as "user"
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"

  @Connection_noRealm
  Scenario: To verify that Realm is mandatory for Connections
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "realm" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"

  @Connection_invalidRealm
  Scenario: To verify that Realm is mandatory for Connections
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "realm" field as "peerius"
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"

  @Connection_noPassword
  Scenario: To verify that Password is mandatory for Connections
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "password" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"

  @Connection_invalidPassword
  Scenario: To verify that Password is mandatory for Connections
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "password" field as "123"
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"

  @Create_ConnectionsForTest
  Scenario Outline: Create Test Connection For Duplicate scenario
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I Create Test Connection "<Connection>" for ESP "Teradata (ECircle)"
    When I click on button "Save ESP Connection"
    Then I should see Message "Successfully saved"

    Examples: Connections For Test
      | Connection     | ESP                |
      | TestConnection | Teradata (ECircle) |
      | ECircle        | Teradata (ECircle) |

  @Connetion_DuplicateName
  Scenario: Validate Duplicate Name Error
    Given I click on button "ESP Connections"
    When I click on button "Add an ESP connection"
    And I Create Test Connection "TestConnection" for ESP "Teradata (ECircle)"
    When I click on button "Save ESP Connection"
    And I Wait for Element "notification"
    Then I should see Message "There is already a connection with name 'TestConnection'"

  @Actions_nameMandatory
  Scenario: To verify that name is mandatory for Actions
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I set "action-name" field as ""
    When I click on button "Test ESP Action"
    Then I should see Message "Name is required."

  @Actions_verifyActionTypesDisplay
  Scenario: To verify that for adding new action Action Types are displayed after selecting connection
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I select ESP connection as "ECircle"
    Then I should see Action Types Menu

  @DeactiveConnetionTest
  Scenario: To verify that Action can not be tested if Connection selected is inactive
    Given I click on button "ESP Connections"
    And I deactivate "TestConnection" ESP Connection
    Then I should see Message "Switched off"

  @TestActionInactiveConnetion
  Scenario: Test Action with Inactive Connection
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set "action-name" field as "Test"
    And I select ESP connection as "TestConnection"
    And I click on button "Send message"
    And I enter "test@peerius.com" in field "email_address"
    And I enter "1800403818" in field "Message ID"
    When I click on button "Test ESP Action"
    Then I should see Message "Test has failed because the ESP Connection is not active"

  @activateTestConnection
  Scenario: Activate a deactivated ESP Connection
    Given I activate "TestConnection" ESP Connection
    Then I should see Message "Switched on"

  @Actions_AddUserInvalidGroupID
  Scenario: To verify that Action to Add New User to Group fails for invalid Group ID
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I click on button "Add user to group"
    And I enter "123abc" in field "Group ID"
    When I click on button "Test ESP Action"
    Then I should see Message "Could not add the user"

  @Actions_RemoveUserInvalidGroupID
  Scenario: To verify that Action to Add New User to Group fails for invalid Group ID
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I click on button "Add user to group"
    And I enter "123abc" in field "Group ID"
    When I click on button "Test ESP Action"
    Then I should see Message "Could not add the user"

  @Actions_InvalidmessageID
  Scenario: To verify that Action to Send Message To User fails for invalid Message ID
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I enter "123abc" in field "Message ID"
    When I click on button "Test ESP Action"
    Then I should see Message "Could not send message with ID"

  @Actions_CreateAction
  Scenario: To verify that duplicate name for Action is not allowed
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I Create Test Action "TestAction" for ESP "TestConnection"
    And I click on button "Save ESP Action"

  @Actions_DuplicateName
  Scenario: Verify Duplicate Name Error
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I Create Test Action "TestAction" for ESP "TestConnection"
    When I click on button "Save ESP Action"
    Then I should see Message "There is already an action with name 'TestAction'"

  @Triggers_nameMandatory
  Scenario: To verify that name is mandatory for Triggers
    When I click on button " Add a Trigger"
    And I set data for new "Trigger"
    And I set "triggers_name" field as ""
    When I click on button "Save Trigger"
    Then I should see Message "Please provide a name for your new Trigger."

  @Triggers_MinOfInactivityMandatory
  Scenario: To verify that Min of Inactivity is mandatory for Triggers
    When I click on button " Add a Trigger"
    And I set data for new "Trigger"
    And I enter "" in field "after"
    When I click on button "Save Trigger"
    Then I should see Message "Please provide the number of inactivity minutes"

  @Triggers_criteriaMandatory
  Scenario: To verify that Criteria is mandatory for Triggers
    When I click on button " Add a Trigger"
    And I set data for new "Trigger"
    And I delete the Criteria
    When I click on button "Save Trigger"
    Then I should see Message "Invalid configuration : Triggers must have some trigger criteria"

  @Triggers_ValidateDuplicate
  Scenario Outline: Validate Duplicate Name Error
    And I click on button "Add a Trigger"
    And I set data for new "Trigger"
    And I click on button "Save Trigger"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Message                                                        |
      | Successfully saved                                             |
      | Invalid configuration : A trigger called 'Test' already exists |

  @DeleteValidationTrigger
  Scenario: Delete ESP Trigger
    Given I Delete ESP Trigger "Test"
    Then I Should not See ESP Trigger "Test" on Mail Triggers Page

  @DeleteFinalAction
  Scenario: Delete ESP Action
    Given I click on button "ESP Actions"
    And I Delete ESP Action "TestAction"
    Then I Should not See ESP Action "TestAction" on Mail Triggers Page

  @DeleteValidationConnection
  Scenario Outline: Delete ESP Connections After Test
    Given I click on button "ESP Connections"
    And I Delete ESP Connection "<Connection>"
    Then I Should not See ESP Connection "<Connection>" on Mail Triggers Page

    Examples: Test Connections
      | Connection     |
      | TestConnection |
      | ECircle        |
