Feature: E2E Test for Triggers

	Background:pre-requisite
	Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    
   @E2EConnectionSetup
  	Scenario: Create an E2E ESP Connection
    Given click on button "ESP Connections"
   	And click on button "Add an ESP connection"
    And I Create Connection "E2EConnection" for ESP "ECircle"
    Then I Should See ESP Connection "E2EConnection" on Mail Triggers Page
    
    @E2EActionSetup
    Scenario: Create an E2E ESP Action
    When click on button "ESP Actions"
    And click on button "Add an ESP Action"
    And I Create Action "E2EAction" for ESP "E2EConnection"
    Then I Should See ESP Action "E2EAction" on Mail Triggers Page
    
    @E2EESPTriggerSetup
 	Scenario: Create an E2E ESP Trigger
    When click on button " Add a Trigger"
    And I Create Trigger with name "E2ETrigger" for "E2EAction" from "2"
    Then I Should See ESP Trigger "E2ETrigger" on Mail Triggers Page
    Given I activate "E2ETrigger" ESP Trigger
	
	
	  

    
    
    