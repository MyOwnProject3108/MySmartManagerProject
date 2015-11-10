Feature: E2E Test for Triggers

  Background: pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"
    When I click on "Triggers" option in "Triggers"
    Then I should be on Mail "Triggers" page

  @E2EConnectionSetup
  Scenario: Create an E2E ESP Connection
    Given I click on button "ESP Connections"
    And I click on button "Add an ESP connection"
    And I Create Connection "E2EConnection" for ESP "ECircle"
    Then I Should See ESP Connection "E2EConnection" on Mail Triggers Page

  @E2EActionSetup
  Scenario: Create an E2E ESP Action
    Given I click on button "ESP Actions"
    And I click on button "Add an ESP Action"
    And I Create Action "E2EAction" for ESP "E2EConnection"
    Then I Should See ESP Action "E2EAction" on Mail Triggers Page

  @E2EESPTriggerSetup
  Scenario: Create an E2E ESP Trigger
    Given I click on button " Add a Trigger"
    And I Create Trigger "Abandoned Browse" with name "E2ETrigger" for "E2EAction" and "0" period
    Then I Should See ESP Trigger "E2ETrigger" on Mail Triggers Page
    Given I activate "E2ETrigger" ESP Trigger

  @TriggerEmailCampaignSetup
  Scenario: Create TriggerEmail Campaign
    Given I click on "Create a new campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    Given I Create Simple Mail Campaign with name "E2ETriggerEmail"
    Given I goto Mail Campaign "E2ETriggerEmail"
    And I click on link "2. Configuration"
    And I Set "Triggered Email" at position "1"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved."

  @E2EAbandonedBrowseTest
  Scenario: E2E Test for Abandoned Browse
    Given I Register with random Email address as "peeriustest"
    And I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10641946.html"
    Given I Goto Registered Random Inbox
    And I click on the Email "LanderTest"
    And Switch Frame "rendermail"

  @DeleteE2ETrigger
  Scenario: Delete AbandonedBasket Trigger After Tests
    Given I Delete ESP Trigger "E2ETrigger"
    Then I Should not See ESP Trigger "E2ETrigger" on Mail Triggers Page

  @DeleteE2EAction
  Scenario: Delete ESP Action After Test
    Given I click on button "ESP Actions"
    And I Delete ESP Action "E2EAction"
    Then I Should not See ESP Action "E2EAction" on Mail Triggers Page

  @DeleteConnectionE2E
  Scenario: Delete ESP Connections After Test
    Given I click on button "ESP Connections"
    And I Delete ESP Connection "E2EConnection"
    Then I Should not See ESP Connection "E2EConnection" on Mail Triggers Page
