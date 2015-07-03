Feature: E2E Test for Triggers

	Background:pre-requisite
	Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"
        
   @E2EConnectionSetup
  	Scenario: Create an E2E ESP Connection
  	Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    Given click on button "ESP Connections"
   	And click on button "Add an ESP connection"
    And I Create Connection "E2EConnection" for ESP "ECircle"
    Then I Should See ESP Connection "E2EConnection" on Mail Triggers Page
    
    @E2EActionSetup
    Scenario: Create an E2E ESP Action
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Actions"
    And click on button "Add an ESP Action"
    And I Create Action "E2EAction" for ESP "E2EConnection"
    Then I Should See ESP Action "E2EAction" on Mail Triggers Page
    
    @E2EESPTriggerSetup
 	Scenario: Create an E2E ESP Trigger
 	Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button " Add a Trigger"
    And I Create Trigger with name "E2ETrigger" for "E2EAction" from "2"
    Then I Should See ESP Trigger "E2ETrigger" on Mail Triggers Page
    Given I activate "E2ETrigger" ESP Trigger
    
    @TriggerEmailCampaignSetup
    Scenario: Create TriggerEmail Campaign
    Given I click on "Create a New Campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    Given I Create Simple Mail Campaign with name "TriggerEmailTest"
    Given I goto Mail Campaign "TriggerEmailTest"
    And I click on link "2. Configuration"
    And I Set "Triggered Email" at position "1"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."
    
    
    @E2EAbandonedBrowseTest
    Scenario: E2E Test for Abandoned Browse
    Given I Register with random Email address as "Peeriustest"
    And I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10641946.html"
    Given I Goto Registered Random Inbox
    And I click on the Email "Lander Test"
    And Switch Frame "rendermail"
    
     
	
	
	  

    
    
    