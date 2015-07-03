Feature: SMART-Triggers Test

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"
     Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page

  @CreateESPConnection
  Scenario Outline: Create an ESP Connection
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
   Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I Create Test Connection "ECircleTest" for ESP "Teradata (ECircle)"
    Then I Should not See ESP Connection "ECircleTest" on Mail Triggers Page
    
    @NoDeleteESPConnection
  Scenario: Click No to Delete ESP Connection
	Given click on button "ESP Connections"
    And I click No to Delete ESP Connection "ECircle"
    Then I Should See ESP Connection "ECircle" on Mail Triggers Page

  @DeactiveESPConnection
  Scenario: Deactive ESP Connection
	Given click on button "ESP Connections"
    And I deactivate "ECircle" ESP Connection
    Then I should see Message "Switched off"

  @ActivatedeactivatedESPConnection
  Scenario: Activate a deactivated ESP Connection
    Given click on button "ESP Connections"
    And I activate "ECircle" ESP Connection
    Then I should see Message "Switched on"
    
    
  @EditDeleteButtonConnection
  Scenario: Verify delete button is disabled for Edit ESP Connection
	Given click on button "ESP Connections"
    And I click Edit button for "ECircle" ESP Connection
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @EditESPConnection
  Scenario: Edit ESP Connection
   	Given click on button "ESP Connections"
    Given I Edit ESP Connection "ECircle"
    Then I Should See ESP Connection "AutoECircleConnection" on Mail Triggers Page

  @DeleteESPConnection
  Scenario: Delete ESP Connection
  	Given click on button "ESP Connections"
    Given I Delete ESP Connection "AutoECircleConnection"
    Then I Should not See ESP Connection "AutoECircleConnection" on Mail Triggers Page

  @NewDeleteButtonConnection
  Scenario: Verify delete button is disabled for new ESP Connection
    Given click on button "ESP Connections"
   And click on button "Add an ESP connection"
    And I Create Connection "ECircle" for ESP "ECircle"
    Then I Should See disabled delete button for "ECircle" ESP Connection

  @CreateESPAction
  Scenario Outline: Create an ESP Action
    When click on button "ESP Actions"
    And click on button "Add an ESP Action"
    And I Create Action "<Name>" for ESP "<Connection>"
    Then I Should See ESP Action "<Verification>" on Mail Triggers Page

    Examples: Actions
      | Name      | Connection | Verification|
      | ECircle   | ECircle    | ECircle     |
      | SmartCast | SmartCast  | SmartCast   |
      | Silverpop | Silverpop  | Silverpop   |

  @TestOnlyESPAction
  Scenario: Create Test only ESP Connection
    Given I click on button "ESP Actions"
    And click on button "Add an ESP Action"
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
    Given I Edit ESP Action "ECircle"
    Then I Should See ESP Action "AutoECircleAction" on Mail Triggers Page
    
   @DeleteESPAction
   Scenario: Delete ESP Action
    Given I click on button "ESP Actions"
    Given I Delete ESP Action "AutoECircleAction"
    Then I Should not See ESP Action "AutoECircleAction" on Mail Triggers Page
    
  @NewDeleteButtonAction
  Scenario: Verify delete button is disabled for new ESP Action
    Given I click on button "ESP Actions"
   And click on button "Add an ESP Action"
	And I Create Action "ECircle" for ESP "ECircle"
    Then I Should See disabled delete button for "ECircle" ESP Action
    
  @CreateESPTrigger
  Scenario Outline: Create an ESP Trigger
    When click on button " Add a Trigger"
    And I Create Trigger with name "<TriggerName>" for "<ESPAction>" from "<Position>"

    Examples: Triggers
      | TriggerName      | ESPAction | Position|
      | Abandoned Browse | ECircle   | 2       |
      | Abandoned Basket | SmartCast | 1       |
      
         
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
   Scenario:  Edit ESP Trigger
   Given I Edit ESP Trigger "Abandoned Browse"  
   Then I Should See ESP Trigger "Edit Abandoned Browse" on Mail Triggers Page
   
   @DeleteESPTrigger
   Scenario: Delete ESP Trigger
   Given I Delete ESP Trigger "Edit Abandoned Browse"
   Then I Should not See ESP Trigger "Edit Abandoned Browse" on Mail Triggers Page
    
   @NewDeleteButtonTrigger
   Scenario: Verify delete button is disabled for new ESP Trigger
    Given click on button "Add a Trigger"
    And I Create Trigger with name "Abandoned Browse" for "ECircle" from "2"
    Then I Should See disabled delete button for "Abandoned Browse" ESP Trigger
   
 #Error validation scenarios start here
 
  Feature: SMART-Triggers Test

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page

  @Connection_nameMandatory
  Scenario: To verify that name is mandatory for Connections
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "name" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Name is required."
    
  @Connection_noUserName
  Scenario: To verify that UserName is mandatory for Connections
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "username" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"
    
  @Connection_invalidUserName
  Scenario: To verify that incorrect UserName is not accepted for Connections
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "username" field as "user"
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"
    
  @Connection_noRealm
  Scenario: To verify that Realm is mandatory for Connections
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "realm" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"
    
  @Connection_invalidRealm
  Scenario: To verify that Realm is mandatory for Connections
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "realm" field as "peerius"
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"
    
  @Connection_noPassword
  Scenario: To verify that Password is mandatory for Connections
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "password" field as ""
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"
    
  @Connection_invalidPassword
  Scenario: To verify that Password is mandatory for Connections
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I set data for new "Connection"
    And I set "password" field as "123"
    When I click on button "Test ESP Connection"
    Then I should see Message "Could not connect to ecircle using specified credentials"
    
  @Connection_duplicateName
  Scenario: To verify that duplicate name for connection is not allowed
    Given click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I Create Test Connection "TestConnection" for ESP "Teradata (ECircle)"
    And click on button "Add an ESP connection"
    And I Create Test Connection "TestConnection" for ESP "Teradata (ECircle)"
    When I click on button "Save ESP Connection"
    Then I should see Message "There is already a connection with name 'TestConnection'"
    
  
  @Actions_nameMandatory
  Scenario: To verify that name is mandatory for Actions
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I set "action-name" field as ""
    When I Test and Save ESP Action
    Then I should see Message "Name is required."
    
  @Actions_verifyActionTypesDisplay
  Scenario: To verify that for adding new action Action Types are displayed after selecting connection
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I select ESP connection as "ECircle"
    Then I should see Action Types Menu
    
  @Actions_inactiveConnection
  Scenario: To verify that Action can not be tested if Connection selected is inactive
    Given click on button "ESP Connections"
    And I deactivate "TestConnection" ESP Connection
    Then I should see Message "Switched off"
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I select ESP connection as "TestConnection"
    When I click on button "Test ESP Action"
    Then I should see Message "Test has failed because the ESP Connection is not active" 
    And I activate "TestConnection" ESP Connection
    Then I should see Message "Switched on"
    
  @Actions_AddUserInvalidGroupID
  Scenario: To verify that Action to Add New User to Group fails for invalid Group ID
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I click on button "Add user to group"
    And I enter "123" in field "Group ID" 
    When I Test and Save ESP Action
    Then I should see Message ""
  
  @Actions_RemoveUserInvalidGroupID
  Scenario: To verify that Action to Add New User to Group fails for invalid Group ID
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I click on button "Add user to group"
    And I enter "123" in field "Group ID" 
    When I click on button "Test ESP Action"
    Then I should see Message "Test failed."
    
  @Actions_InvalidmessageID
  Scenario: To verify that Action to Send Message To User fails for invalid Message ID
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I set data for new "Action"
    And I enter "123" in field "Message ID" 
    When I click on button "Test ESP Action"
    Then I should see Message "Test failed."
    
  @Actions_duplicateName
  Scenario: To verify that duplicate name for Action is not allowed
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I Create Test Action "TestAction" for ESP "TestConnection"
    And click on button "Save ESP Action"
    And I click on button "Add an ESP Action"
    And I Create Test Action "TestAction" for ESP "TestConnection"
    When I click on button "Save ESP Action"
    Then I should see Message "There is already an action with name 'TestAction'"
    
  @Triggers_nameMandatory
  Scenario: To verify that name and Min of Inactivity are mandatory for Triggers
    When I click on button " Add a Trigger"
    And I set data for new "Trigger"
    And I set "triggers_name" field as ""
    And I set "triggers_name" field as ""
    When I click on button "Save Trigger"
    Then I should see Message "Please provide a name for your new Trigger."

  @Triggers_MinOfInactivityMandatory
  Scenario: To verify that name and Min of Inactivity are mandatory for Triggers
    When I click on button " Add a Trigger"
    And I set data for new "Trigger"
    And I set MinOfInactivity field as blank
    When I click on button "Save Trigger"
    Then I should see Message "Please provide a name for your new Trigger."   
    
  @Triggers_criteriaMandatory
  Scenario: To verify that Criteria is mandatory for Triggers
  	When I click on button " Add a Trigger"
    And I set data for new "Trigger"
    And I delete the Criteria
    When I click on button "Save Trigger"
    Then I should see Message "Invalid configuration : Triggers must have some trigger criteria" 
    
  @Triggers_duplicateName
  Scenario Outline: To verify that duplicate name for Triggers is not allowed
    When I click on button " Add a Trigger"
    And I set data for new "Trigger" 
    And I click on button "Save Trigger"
    And I click on button " Add a Trigger"
    And I set data for new "Trigger" 
    And I click on button "Save Trigger"
    And I Create Trigger with name "TestTrigger" for "TestAction"
    Then I should see Message "Invalid configuration : A trigger called 'test' already exists"  
   
  @DeleteValidationConnection
  Scenario: Delete ESP Connection
  	Given click on button "ESP Connections"
    Given I Delete ESP Connection "TestConnection"
    Then I Should not See ESP Connection "TestConnection" on Mail Triggers Page
    
  @DeleteValidationAction
   Scenario: Delete ESP Action
    Given I click on button "ESP Actions"
    Given I Delete ESP Action "TestAction"
    Then I Should not See ESP Action "ECircle" on Mail Triggers Page
    
  @DeleteValidationTrigger
   Scenario: Delete ESP Trigger
   Given I Delete ESP Trigger "Abandoned Browse"
   Then I Should not See ESP Trigger "TestTrigger" on Mail Triggers Page
    
  
      
      
      
      
   	
    
